package rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

/** RSS szabványnak megfelelő XML fájlok beolvasása */

public class RSSReader {
    final String TITLE = "title";
    final String DESC = "description";
    final String LINK = "link";
    final String ITEM = "item";
    final String PUB_DATE = "pubDate";
    final String CONTENT = "encoded";

    final URL url;

    public RSSReader(String url) {
        try{
            this.url = new URL(url);
        } catch (MalformedURLException ex){
            throw new RuntimeException(ex);
        }
    }

    /** RSS Feed tartalmának beolvasása */

    public Feed readFeed() {
        Feed feed = null;
        try{
            boolean isFeedHeader = true;
            String description = "";
            String title = "";
            String link = "";
            Date pubDate = null;
            String content = "";

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            inputFactory.setProperty(XMLInputFactory.IS_COALESCING, true); // Speciális karakterek beolvasásához
            InputStream inputStream = readStream();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if(event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart){
                        case ITEM:
                            if(isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, url.toString());
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESC:
                            description = getCharacterData(event, eventReader);
                            break;
                        case CONTENT:
                            content = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            String data = getCharacterData(event, eventReader);
                            pubDate = convertToDate(data);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedItem item = new FeedItem(title);
                        item.setDescription(description);
                        item.setLink(link);
                        item.setPubDate(pubDate);
                        feed.addFeedItem(item);

                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
            inputStream.close();
            eventReader.close();

        } catch (XMLStreamException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feed;
    }

    /** Egy Feed-hez tartozó FeedItemek beolvasása */

    public List<FeedItem> readFeedItems(){
        List<FeedItem> result = new ArrayList<>();

        try {
            boolean isFeedItemHeader = false;
            String description = "";
            String title = "";
            String link = "";
            Date pubDate = null;
            String content = "";

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            inputFactory.setProperty(XMLInputFactory.IS_COALESCING, true); // Speciális karakterek beolvasásához
            InputStream inputStream = readStream();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if(event.isStartElement()) { // Egy kezdő <item> tag
                    String localPart = event.asStartElement().getName().getLocalPart();
                    if(localPart == ITEM) isFeedItemHeader = true;
                    if(isFeedItemHeader){
                        switch (localPart){
//                        case ITEM:
//                            if(isFeedItemHeader == false) {  // Ha eddig nem <item> volt de most azt talált akkor legyen <item>
//                                isFeedItemHeader = true;
//                                //feed = new Feed(title, link, description);
//                            }
//                            event = eventReader.nextEvent();
//                            break;
                            case TITLE:
                                title = getCharacterData(event, eventReader);
                                break;
                            case DESC:
                                description = getCharacterData(event, eventReader);
                                break;
                            case CONTENT:
                                content = getCharacterData(event, eventReader);
                                break;
                            case LINK:
                                link = getCharacterData(event, eventReader);
                                break;
                            case PUB_DATE:
                                String data = getCharacterData(event, eventReader);
                                pubDate = convertToDate(data);
                                break;
                        }
                    }
                } else if (event.isEndElement()) {  // Egy záró </item> tag
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedItem item = new FeedItem(title);
                        item.setDescription(description);
                        item.setLink(link);
                        item.setPubDate(pubDate);
                        //feed.addFeedItem(item);
                        result.add(item);

                        isFeedItemHeader = false;
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
            inputStream.close();
            eventReader.close();
        } catch (XMLStreamException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if(event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream readStream() {
        try {
            return url.openStream();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Date convertToDate(String data) {
        DateFormat dateFormat = new SimpleDateFormat("E, d MMM y HH:mm:ss Z", Locale.US);
        Date result = null;
        try {
            result =  dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
