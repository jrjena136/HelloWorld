package com.jyoti.demo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Test1 {
    public static void main(String[] args) {
        /*try {
            File file = new File("/Users/jyotiranjan.jena/Desktop/localconfig.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList nList = doc.getElementsByTagName("key");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) node;
                    System.out.println("Key : " + ele.getAttribute("name"));
                    System.out.println("Value : " + ele.getElementsByTagName("value").item(0).getTextContent());
                    System.out.println("******************************");
                }
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        // Unmarshalling
        try {
          //getting the xml file to read
            File file = new File("/Users/jyotiranjan.jena/Desktop/localconfig.xml");
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(LocalConfig.class);
            //creating the unmarshall object
            Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
            //calling the unmarshall method
            LocalConfig localConfig=(LocalConfig) unmarshallerObj.unmarshal(file);
            Map<String, String> localconfigs = new HashMap<String, String>();
            for (Key key : localConfig.keys) {
                localconfigs.put(key.getName(), key.getValue());
            }
            System.out.println(localconfigs);
            System.out.println(localconfigs.get("mailboxd_java_options"));
        } catch(Exception e) {
            
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlRootElement(name = "localconfig")
    public static class LocalConfig {
        @XmlElement(name = "key")
        private List<Key> keys;

        public List<Key> getKey() {
            return keys;
        }

        public void setKey(List<Key> keys) {
            this.keys = keys;
        }

        public LocalConfig() {
            
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Key {
        @XmlAttribute
        private String name;
        @XmlElement
        private String value;

        public Key() {
            
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
