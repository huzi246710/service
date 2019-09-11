package com.wwq.common.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * xml文件处理公共类
 *
 * @author wangwq
 * @since 2019/08/15
 */
public final class XmlUtil {
    private XmlUtil() {
    }

    /**
     * 获取xml文档
     *
     * @param xmlStream     xml文件流
     * @return  节点数
     * @throws Exception    Exception
     */
    public static Document getDocument(InputStream xmlStream) throws Exception {
        DocumentBuilderFactory buildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = buildFactory.newDocumentBuilder();
        return builder.parse(xmlStream);
    }

    /**
     * 获取xml文档
     *
     * @param xmlPath   xmlPath
     * @throws Exception    Exception
     * @return  Document
     */
    public static Document getDocument(String xmlPath) throws Exception {
        DocumentBuilderFactory buildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = buildFactory.newDocumentBuilder();
        InputStream in = new FileInputStream(xmlPath);
        Reader reader = new InputStreamReader(in, "utf-8");
        InputSource inSource = new InputSource(reader);
        return builder.parse(inSource);
    }

    /**
     * 获取xml文档
     *
     * @param xmlFile   xmlFile
     * @throws Exception    Exception
     * @return  Document
     */
    public static Document getDocument(File xmlFile) throws Exception {
        return getDocument(new FileInputStream(xmlFile));
    }

    /**
     * 获取xml文档对象
     *
     * @param xmlStr    xmlStr
     * @return  Document
     * @throws Exception    Exception
     */
    public static Document getXmlDocument(String xmlStr) {
        try {
            return getDocument(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
        }catch (Exception e){
            e.printStackTrace();
            throw new SecurityException("this is a invalid xmlString");
        }
    }

    /**
     * 将xml文档对象写入XML文件
     *
     * @param doc     xml文档对象
     * @param xmlName xml文件名
     * @throws Exception    Exception
     */
    public static void writeToXml(Document doc, String xmlName) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer tf = factory.newTransformer();
        // 处理生成xml文件时, 进行格式化, 不需要处理
        // tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(doc), new StreamResult(new File(xmlName)));
    }

    /**
     * 将xml文档写入文件, 如果含有特殊字符的话, 不会被转义
     *
     * @param doc           doc
     * @param fileName      文件名称
     * @throws Exception    Exception
     */
    public static void writeToFile(Document doc, String fileName) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer tf = factory.newTransformer();
        // 处理特殊字符时是否需要进行转义
        tf.setOutputProperty(OutputKeys.METHOD, "html");
        tf.transform(new DOMSource(doc), new StreamResult(new File(fileName)));
    }

    /**
     * 创建新的xml文档
     *
     * @return  Document
     */
    public static Document newDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.newDocument();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取子节点列表
     *
     * @param root  root
     * @return  List<Element>
     */
    public static List<Element> getChildElements(Element root) {
        if (null == root) {
            return Collections.EMPTY_LIST;
        }
        NodeList nodeList = root.getChildNodes();
        if (null == nodeList || 0 == nodeList.getLength()) {
            return Collections.EMPTY_LIST;
        }
        List<Element> result = new ArrayList<Element>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (Node.ELEMENT_NODE == nodeList.item(i).getNodeType()) {
                Element child = (Element) nodeList.item(i);
                result.add(child);
            }
        }
        return result;
    }

    /**
     * 查找子节点
     *
     * @param parent        parent
     * @param nodeName      nodeName
     * @return  Element
     */
    public static Element getElement(Element parent, String nodeName) {
        NodeList nodeList = parent.getChildNodes();
        if (null != nodeList && 0 != nodeList.getLength()) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (Node.ELEMENT_NODE == nodeList.item(i).getNodeType()) {
                    if (nodeList.item(i).getNodeName().equals(nodeName)) {
                        return (Element) nodeList.item(i);
                    }
                }
            }
        }
        return null;
    }
   /* public static Map<String,String> getElement(Document document, String parentNode,
                                                String childNode,List<String> elements){
        Map<String,String> resultMap = new HashMap<>();
        NodeList nodeList = document.getElementsByTagName(parentNode);
        Element node = (Element)nodeList.item(0);
        NodeList list = node.getElementsByTagName(childNode);
        for(int t = 0; t < list.getLength(); t++) {
            Element et = (Element) list.item(t);
            for (String emp:elements){
                String value = et.getElementsByTagName(emp).item(0).getFirstChild().getNodeValue();
                resultMap.put(emp,value);
            }
        }
        return resultMap;
    }*/
    public static void main(String[] args) {
        String str = getXml();
        try {
           /* Document document = getXmlDocument(str);
            List<String> list = new ArrayList<>();
            list.add("IfIndex");
            list.add("SrvID");
            list.add("Encap");
            Map map = getElement(document,"SRVs","SRV",list);*/
           // System.out.println(map);

          /*  NodeList nodeList = document.getElementsByTagName("TUNNEL");
            Element node = (Element)nodeList.item(0);
            NodeList list = node.getElementsByTagName("Tunnel");
            for(int t = 0; t < list.getLength(); t++) {
                Element et = (Element) list.item(t);
                NodeList list2 =et.getElementsByTagName("IPv4Addr");
                for (int i = 0; i < list2.getLength(); i++) {
                    Element test = (Element) list2.item(i);
                    System.out.println(test.getElementsByTagName("SrcAddr").item(0).getFirstChild().getNodeValue());
                    System.out.println(test.getElementsByTagName("DstAddr").item(0).getFirstChild().getNodeValue());
                }
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getXml() {
        String str = "<env:Envelope xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\"> " +
                "   <env:Header> " +
                "      <auth:Authentication env:mustUnderstand=\"true\" xmlns:auth=\"http://www.h3c.com/netconf/base:1.0\"> " +
                "         <auth:AuthInfo>1000015f8c916621ae5023bbe79d8a81d893</auth:AuthInfo> " +
                "      </auth:Authentication> " +
                "   </env:Header> " +
                "   <env:Body> " +
                "      <rpc-reply message-id=\"1\" xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\" xmlns:xc=\"urn:ietf:params:xml:ns:netconf:base:1.0\"> " +
                "         <data> " +
                "            <top xmlns=\"http://www.h3c.com/netconf/config:1.0\"> " +
                "               <BGP> " +
                "                  <CfgSessions> " +
                "                     <CfgSession> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>1.0.0.1</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                        <ASNumber>11</ASNumber> " +
                "                        <ConnectInterface>LoopBack0</ConnectInterface> " +
                "                     </CfgSession> " +
                "                     <CfgSession> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>1.0.0.2</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                        <ASNumber>12</ASNumber> " +
                "                        <ConnectInterface>LoopBack0</ConnectInterface> " +
                "                     </CfgSession> " +
                "                     <CfgSession> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>1.1.68.6</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                        <ASNumber>10006</ASNumber> " +
                "                        <ConnectInterface>LoopBack0</ConnectInterface> " +
                "                     </CfgSession> " +
                "                     <CfgSession> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>91.1.1.0</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                        <ASNumber>21</ASNumber> " +
                "                     </CfgSession> " +
                "                     <CfgSession> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>91.1.1.2</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                        <ASNumber>22</ASNumber> " +
                "                     </CfgSession> " +
                "                     <CfgSession> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>191.1.5.0</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                        <ASNumber>101</ASNumber> " +
                "                     </CfgSession> " +
                "                     <CfgSession> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>191.1.5.2</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                        <ASNumber>102</ASNumber> " +
                "                     </CfgSession> " +
                "                     <CfgSession> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>192.1.6.1</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                        <ASNumber>10006</ASNumber> " +
                "                     </CfgSession> " +
                "                  </CfgSessions> " +
                "                  <Familys> " +
                "                     <Family> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Type>1</Type> " +
                "                     </Family> " +
                "                     <Family> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Type>9</Type> " +
                "                     </Family> " +
                "                  </Familys> " +
                "                  <Instances> " +
                "                     <Instance> " +
                "                        <Name/> " +
                "                        <ASNumber>1005</ASNumber> " +
                "                     </Instance> " +
                "                  </Instances> " +
                "                  <Neighbors> " +
                "                     <Neighbor> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>1</Family> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>91.1.1.0</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                     </Neighbor> " +
                "                     <Neighbor> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>1</Family> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>91.1.1.2</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                     </Neighbor> " +
                "                     <Neighbor> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>1</Family> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>191.1.5.0</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                     </Neighbor> " +
                "                     <Neighbor> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>1</Family> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>191.1.5.2</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                     </Neighbor> " +
                "                     <Neighbor> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>1</Family> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>192.1.6.1</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                     </Neighbor> " +
                "                     <Neighbor> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>9</Family> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>1.0.0.1</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                     </Neighbor> " +
                "                     <Neighbor> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>9</Family> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>1.0.0.2</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                     </Neighbor> " +
                "                     <Neighbor> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>9</Family> " +
                "                        <SessAF>1</SessAF> " +
                "                        <IpAddress>1.1.68.6</IpAddress> " +
                "                        <Mask>255</Mask> " +
                "                     </Neighbor> " +
                "                  </Neighbors> " +
                "                  <Networks> " +
                "                     <Network> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                        <Family>1</Family> " +
                "                        <IpAddress>1.1.125.5</IpAddress> " +
                "                        <Mask>32</Mask> " +
                "                     </Network> " +
                "                  </Networks> " +
                "                  <VRFs> " +
                "                     <VRF> " +
                "                        <Name/> " +
                "                        <VRF/> " +
                "                     </VRF> " +
                "                  </VRFs> " +
                "               </BGP> " +
                "               <Device> " +
                "                  <Base> " +
                "                     <HostName>Core_C_5</HostName> " +
                "                  </Base> " +
                "                  <FanDirections> " +
                "                     <Fan> " +
                "                        <Chassis>9</Chassis> " +
                "                        <Slot>65535</Slot> " +
                "                        <CPUID>65535</CPUID> " +
                "                     </Fan> " +
                "                  </FanDirections> " +
                "                  <TemperatureSensors> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>0</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>1</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>0</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>2</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>0</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>3</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>0</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>4</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>0</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>5</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>1</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>0</SensorType> " +
                "                        <SensorIndex>1</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>1</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>1</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>1</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>2</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>1</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>3</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>1</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>4</SensorIndex> " +
                "                     </Sensor> " +
                "                     <Sensor> " +
                "                        <Chassis>2</Chassis> " +
                "                        <Slot>1</Slot> " +
                "                        <CPUID>0</CPUID> " +
                "                        <SensorType>2</SensorType> " +
                "                        <SensorIndex>5</SensorIndex> " +
                "                     </Sensor> " +
                "                  </TemperatureSensors> " +
                "               </Device> " +
                "               <Domain> " +
                "                  <Domains> " +
                "                     <Domain> " +
                "                        <DomainName>system</DomainName> " +
                "                     </Domain> " +
                "                  </Domains> " +
                "                  <Schemes> " +
                "                     <Scheme> " +
                "                        <DomainName>system</DomainName> " +
                "                        <AaaType>5</AaaType> " +
                "                        <AccessType>5</AccessType> " +
                "                     </Scheme> " +
                "                     <Scheme> " +
                "                        <DomainName>system</DomainName> " +
                "                        <AaaType>0</AaaType> " +
                "                        <AccessType>5</AccessType> " +
                "                     </Scheme> " +
                "                     <Scheme> " +
                "                        <DomainName>system</DomainName> " +
                "                        <AaaType>4</AaaType> " +
                "                        <AccessType>5</AccessType> " +
                "                     </Scheme> " +
                "                  </Schemes> " +
                "               </Domain> " +
                "               <Ifmgr> " +
                "                  <Interfaces> " +
                "                     <Interface> " +
                "                        <IfIndex>5785</IfIndex> " +
                "                        <PortLayer>2</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>5835</IfIndex> " +
                "                        <PortLayer>2</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>5845</IfIndex> " +
                "                        <PortLayer>2</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>5915</IfIndex> " +
                "                        <PortLayer>2</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>5925</IfIndex> " +
                "                        <PortLayer>2</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46434</IfIndex> " +
                "                        <ConfigMTU>1536</ConfigMTU> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46435</IfIndex> " +
                "                        <ConfigMTU>1536</ConfigMTU> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46436</IfIndex> " +
                "                        <ConfigMTU>64000</ConfigMTU> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46437</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46438</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46439</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46440</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46441</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46442</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46443</IfIndex> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46444</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46445</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46446</IfIndex> " +
                "                        <AdminStatus>2</AdminStatus> " +
                "                        <PortLayer>1</PortLayer> " +
                "                     </Interface> " +
                "                     <Interface> " +
                "                        <IfIndex>46464</IfIndex> " +
                "                        <ConfigMTU>1536</ConfigMTU> " +
                "                     </Interface> " +
                "                  </Interfaces> " +
                "                  <PortIsolation> " +
                "                     <Groups> " +
                "                        <Group> " +
                "                           <IsolatedGroupID>1</IsolatedGroupID> " +
                "                        </Group> " +
                "                     </Groups> " +
                "                  </PortIsolation> " +
                "               </Ifmgr> " +
                "               <IPV4ADDRESS> " +
                "                  <Ipv4Addresses> " +
                "                     <Ipv4Address> " +
                "                        <IfIndex>5785</IfIndex> " +
                "                        <Ipv4Address>192.1.6.0</Ipv4Address> " +
                "                        <Ipv4Mask>255.255.255.254</Ipv4Mask> " +
                "                        <AddressOrigin>1</AddressOrigin> " +
                "                     </Ipv4Address> " +
                "                     <Ipv4Address> " +
                "                        <IfIndex>5835</IfIndex> " +
                "                        <Ipv4Address>191.1.5.1</Ipv4Address> " +
                "                        <Ipv4Mask>255.255.255.254</Ipv4Mask> " +
                "                        <AddressOrigin>1</AddressOrigin> " +
                "                     </Ipv4Address> " +
                "                     <Ipv4Address> " +
                "                        <IfIndex>5845</IfIndex> " +
                "                        <Ipv4Address>191.1.5.3</Ipv4Address> " +
                "                        <Ipv4Mask>255.255.255.254</Ipv4Mask> " +
                "                        <AddressOrigin>1</AddressOrigin> " +
                "                     </Ipv4Address> " +
                "                     <Ipv4Address> " +
                "                        <IfIndex>5915</IfIndex> " +
                "                        <Ipv4Address>91.1.1.1</Ipv4Address> " +
                "                        <Ipv4Mask>255.255.255.254</Ipv4Mask> " +
                "                        <AddressOrigin>1</AddressOrigin> " +
                "                     </Ipv4Address> " +
                "                     <Ipv4Address> " +
                "                        <IfIndex>5925</IfIndex> " +
                "                        <Ipv4Address>91.1.1.3</Ipv4Address> " +
                "                        <Ipv4Mask>255.255.255.254</Ipv4Mask> " +
                "                        <AddressOrigin>1</AddressOrigin> " +
                "                     </Ipv4Address> " +
                "                     <Ipv4Address> " +
                "                        <IfIndex>46273</IfIndex> " +
                "                        <Ipv4Address>16.1.157.32</Ipv4Address> " +
                "                        <Ipv4Mask>255.255.255.0</Ipv4Mask> " +
                "                        <AddressOrigin>1</AddressOrigin> " +
                "                     </Ipv4Address> " +
                "                     <Ipv4Address> " +
                "                        <IfIndex>46464</IfIndex> " +
                "                        <Ipv4Address>1.1.125.5</Ipv4Address> " +
                "                        <Ipv4Mask>255.255.255.255</Ipv4Mask> " +
                "                        <AddressOrigin>1</AddressOrigin> " +
                "                     </Ipv4Address> " +
                "                  </Ipv4Addresses> " +
                "               </IPV4ADDRESS> " +
                "               <IRF> " +
                "                  <Configuration> " +
                "                     <MacPersist>2</MacPersist> " +
                "                  </Configuration> " +
                "               </IRF> " +
                "               <L2VPN> " +
                "                  <ACs> " +
                "                     <AC> " +
                "                        <IfIndex>2</IfIndex> " +
                "                        <SrvID>3</SrvID> " +
                "                        <VsiName>BD-5710YZX00023418</VsiName> " +
                "                     </AC> " +
                "                     <AC> " +
                "                        <IfIndex>46439</IfIndex> " +
                "                        <SrvID>4</SrvID> " +
                "                        <VsiName>BD-5710YZX00023419</VsiName> " +
                "                     </AC> " +
                "                     <AC> " +
                "                        <IfIndex>46437</IfIndex> " +
                "                        <SrvID>5</SrvID> " +
                "                        <VsiName>BD-5710YZX00023417</VsiName> " +
                "                     </AC> " +
                "                  </ACs> " +
                "                  <Base> " +
                "                     <Enable>true</Enable> " +
                "                  </Base> " +
                "                  <SRVs> " +
                "                     <SRV> " +
                "                        <IfIndex>5</IfIndex> " +
                "                        <SrvID>1</SrvID> " +
                "                        <Encap>13</Encap> " +
                "                        <SVlanRange>1800</SVlanRange> " +
                "                        <CVlanRange>3001</CVlanRange> " +
                "                     </SRV> " +
                "                     <SRV> " +
                "                        <IfIndex>5</IfIndex> " +
                "                        <SrvID>2</SrvID> " +
                "                        <Encap>4</Encap> " +
                "                        <SVlanRange>101</SVlanRange> " +
                "                     </SRV> " +
                "                     <SRV> " +
                "                        <IfIndex>5</IfIndex> " +
                "                        <SrvID>4</SrvID> " +
                "                        <Encap>4</Encap> " +
                "                        <SVlanRange>102</SVlanRange> " +
                "                     </SRV> " +
                "                     <SRV> " +
                "                        <IfIndex>5</IfIndex> " +
                "                        <SrvID>5</SrvID> " +
                "                     </SRV> " +
                "                  </SRVs> " +
                "                  <VSIs> " +
                "                     <VSI> " +
                "                        <VsiName>BD-5710YZX00023418</VsiName> " +
                "                        <ArpSuppression>true</ArpSuppression> " +
                "                     </VSI> " +
                "                  </VSIs> " +
                "               </L2VPN> " +
                "               <LAGG> " +
                "                  <LAGGGroups> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>1</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>2</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>3</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>32</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>33</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>100</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>101</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>102</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>103</GroupId> " +
                "                     </LAGGGroup> " +
                "                     <LAGGGroup> " +
                "                        <GroupId>104</GroupId> " +
                "                     </LAGGGroup> " +
                "                  </LAGGGroups> " +
                "               </LAGG> " +
                "               <LLDP> " +
                "                  <GlobalStatus> " +
                "                     <Enable>true</Enable> " +
                "                  </GlobalStatus> " +
                "               </LLDP> " +
                "               <MAC> " +
                "                  <MacVLAN> " +
                "                     <VLANLearn> " +
                "                        <VLANID>1</VLANID> " +
                "                     </VLANLearn> " +
                "                  </MacVLAN> " +
                "               </MAC> " +
                "               <MDC> " +
                "                  <Contexts> " +
                "                     <Context> " +
                "                        <MDCID>1</MDCID> " +
                "                        <Name>Admin</Name> " +
                "                     </Context> " +
                "                  </Contexts> " +
                "               </MDC> " +
                "               <MQC> " +
                "                  <Behaviors> " +
                "                     <Behavior> " +
                "                        <Name>QOS-9-VLAN-3599.1988</Name> " +
                "                     </Behavior> " +
                "                     <Behavior> " +
                "                        <Name>test1</Name> " +
                "                     </Behavior> " +
                "                  </Behaviors> " +
                "                  <CAR> " +
                "                     <Action> " +
                "                        <BehaviorName>QOS-9-VLAN-3599.1988</BehaviorName> " +
                "                        <BandwidthUnit>2</BandwidthUnit> " +
                "                        <CIR>20000</CIR> " +
                "                        <CBS>1250304</CBS> " +
                "                        <EBS>0</EBS> " +
                "                        <GreenAction> " +
                "                           <GreenActionType>1</GreenActionType> " +
                "                        </GreenAction> " +
                "                        <YellowAction> " +
                "                           <YellowActionType>1</YellowActionType> " +
                "                        </YellowAction> " +
                "                        <RedAction> " +
                "                           <RedActionType>10</RedActionType> " +
                "                        </RedAction> " +
                "                     </Action> " +
                "                  </CAR> " +
                "                  <CBMaps> " +
                "                     <CBMap> " +
                "                        <PolicyName>QOS-9</PolicyName> " +
                "                        <ClassifierName>QOS-9-VLAN-3599.1988</ClassifierName> " +
                "                        <BehaviorName>QOS-9-VLAN-3599.1988</BehaviorName> " +
                "                        <Mode>0</Mode> " +
                "                     </CBMap> " +
                "                  </CBMaps> " +
                "                  <Classifiers> " +
                "                     <Classifier> " +
                "                        <Name>1</Name> " +
                "                     </Classifier> " +
                "                     <Classifier> " +
                "                        <Name>QOS-9-VLAN-3599.1988</Name> " +
                "                     </Classifier> " +
                "                     <Classifier> " +
                "                        <Name>test1</Name> " +
                "                     </Classifier> " +
                "                  </Classifiers> " +
                "                  <InterfacePolicy> " +
                "                     <Application> " +
                "                        <IfIndex>1</IfIndex> " +
                "                        <Direction>0</Direction> " +
                "                        <PolicyName></PolicyName> " +
                "                     </Application> " +
                "                     <Application> " +
                "                        <IfIndex>2</IfIndex> " +
                "                        <Direction>0</Direction> " +
                "                     </Application> " +
                "                     <Application> " +
                "                        <IfIndex>46439</IfIndex> " +
                "                        <Direction>0</Direction> " +
                "                        <PolicyName>QOS-3</PolicyName> " +
                "                     </Application> " +
                "                  </InterfacePolicy> " +
                "                  <Policies> " +
                "                     <Policy> " +
                "                        <Name>QOS-5</Name> " +
                "                     </Policy> " +
                "                     <Policy> " +
                "                        <Name>QOS-2</Name> " +
                "                     </Policy> " +
                "                     <Policy> " +
                "                        <Name>QOS-1</Name> " +
                "                     </Policy> " +
                "                     <Policy> " +
                "                        <Name>QOS-9</Name> " +
                "                     </Policy> " +
                "                     <Policy> " +
                "                        <Name>QOS-3</Name> " +
                "                     </Policy> " +
                "                     <Policy> " +
                "                        <Name>QOS-7</Name> " +
                "                     </Policy> " +
                "                     <Policy> " +
                "                        <Name>QOS-4</Name> " +
                "                     </Policy> " +
                "                     <Policy> " +
                "                        <Name>QOS-8</Name> " +
                "                     </Policy> " +
                "                  </Policies> " +
                "                  <Rules> " +
                "                     <Rule> " +
                "                        <ClassName>QOS-9-VLAN-3599.1988</ClassName> " +
                "                        <RuleID>0</RuleID> " +
                "                        <Not>false</Not> " +
                "                        <CustomerVLANID>1800</CustomerVLANID> " +
                "                     </Rule> " +
                "                     <Rule> " +
                "                        <ClassName>QOS-9-VLAN-3599.1988</ClassName> " +
                "                        <RuleID>1</RuleID> " +
                "                        <Not>false</Not> " +
                "                        <ServiceVLANID>3001</ServiceVLANID> " +
                "                     </Rule> " +
                "                     <Rule> " +
                "                        <ClassName>QOS-9-VLAN-3599.1988</ClassName> " +
                "                        <RuleID>2</RuleID> " +
                "                        <Not>false</Not> " +
                "                        <VLANTag>4</VLANTag> " +
                "                     </Rule> " +
                "                  </Rules> " +
                "               </MQC> " +
                "               <RBAC> " +
                "                  <FeatureGroups> " +
                "                     <FeatureGroup> " +
                "                        <Name>L2</Name> " +
                "                     </FeatureGroup> " +
                "                     <FeatureGroup> " +
                "                        <Name>L3</Name> " +
                "                     </FeatureGroup> " +
                "                  </FeatureGroups> " +
                "                  <FeaturesInGroup> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>cfm</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>dldp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>drni</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>eoam</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>erps</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>igmp-snooping</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>lacp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>lldp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>loopbk-detect</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>macsec</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>mld-snooping</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>monitor-link</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>mvrp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>ofp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>pbb</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>rrpp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>smart-link</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>stp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L2</GroupName> " +
                "                        <FeatureName>vlan</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>bgp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>igmp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>isis</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>l3vpn</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>lisp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>mcast-domain</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>mld</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>msdp</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>multicast</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>ospf</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>pim</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>rip</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>route</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>route-policy</FeatureName> " +
                "                     </Feature> " +
                "                     <Feature> " +
                "                        <GroupName>L3</GroupName> " +
                "                        <FeatureName>staticrt</FeatureName> " +
                "                     </Feature> " +
                "                  </FeaturesInGroup> " +
                "                  <Roles> " +
                "                     <Role> " +
                "                        <RoleName>guest-manager</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-0</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-1</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-10</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-11</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-12</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-13</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-14</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-15</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-2</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-3</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-4</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-5</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-6</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-7</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-8</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>level-9</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>mdc-admin</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>mdc-operator</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>network-admin</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>network-operator</RoleName> " +
                "                     </Role> " +
                "                     <Role> " +
                "                        <RoleName>security-audit</RoleName> " +
                "                     </Role> " +
                "                  </Roles> " +
                "               </RBAC> " +
                "               <SNMP> " +
                "                  <System> " +
                "                     <AgentStatus>disable</AgentStatus> " +
                "                  </System> " +
                "               </SNMP> " +
                "               <Syslog> " +
                "                  <OutputRules> " +
                "                     <OutputRule> " +
                "                        <Destination>1</Destination> " +
                "                        <MouduleName>default</MouduleName> " +
                "                     </OutputRule> " +
                "                     <OutputRule> " +
                "                        <Destination>2</Destination> " +
                "                        <MouduleName>default</MouduleName> " +
                "                     </OutputRule> " +
                "                     <OutputRule> " +
                "                        <Destination>3</Destination> " +
                "                        <MouduleName>default</MouduleName> " +
                "                     </OutputRule> " +
                "                     <OutputRule> " +
                "                        <Destination>4</Destination> " +
                "                        <MouduleName>default</MouduleName> " +
                "                     </OutputRule> " +
                "                     <OutputRule> " +
                "                        <Destination>5</Destination> " +
                "                        <MouduleName>default</MouduleName> " +
                "                     </OutputRule> " +
                "                  </OutputRules> " +
                "               </Syslog> " +
                "               <TUNNEL> " +
                "                  <Tunnels> " +
                "                     <Tunnel> " +
                "                        <ID>1</ID> " +
                "                        <Mode>24</Mode> " +
                "                        <IPv4Addr> " +
                "                           <SrcAddr>192.168.228.91</SrcAddr> " +
                "                           <DstAddr>61.130.35.35</DstAddr> " +
                "                        </IPv4Addr> " +
                "                     </Tunnel> " +
                "                     <Tunnel> " +
                "                        <ID>8</ID> " +
                "                        <Mode>24</Mode> " +
                "                        <IPv4Addr> " +
                "                           <SrcAddr>61.164.19.81</SrcAddr> " +
                "                           <DstAddr>61.130.35.36</DstAddr> " +
                "                        </IPv4Addr> " +
                "                     </Tunnel> " +
                "                  </Tunnels> " +
                "                  <VXLANBFDs> " +
                "                     <VXLANBFD> " +
                "                        <ID>1</ID> " +
                "                        <Enable>false</Enable> " +
                "                        <DMAC>00-00-00-00-00-00</DMAC> " +
                "                        <MinRx>400</MinRx> " +
                "                        <MinTx>400</MinTx> " +
                "                     </VXLANBFD> " +
                "                  </VXLANBFDs> " +
                "               </TUNNEL> " +
                "               <UserAccounts> " +
                "                  <Management> " +
                "                     <Accounts> " +
                "                        <Account> " +
                "                           <Name>1</Name> " +
                "                           <HTTP>true</HTTP> " +
                "                           <HTTPS>true</HTTPS> " +
                "                           <TELNET>true</TELNET> " +
                "                        </Account> " +
                "                     </Accounts> " +
                "                     <UserRoles> " +
                "                        <UserRole> " +
                "                           <UserName>1</UserName> " +
                "                           <RoleName>network-admin</RoleName> " +
                "                        </UserRole> " +
                "                        <UserRole> " +
                "                           <UserName>1</UserName> " +
                "                           <RoleName>network-operator</RoleName> " +
                "                        </UserRole> " +
                "                     </UserRoles> " +
                "                  </Management> " +
                "                  <UserGroups> " +
                "                     <Group> " +
                "                        <Name>system</Name> " +
                "                     </Group> " +
                "                  </UserGroups> " +
                "               </UserAccounts> " +
                "               <VCF> " +
                "                  <Role> " +
                "                     <Name>spine</Name> " +
                "                  </Role> " +
                "               </VCF> " +
                "               <VLAN> " +
                "                  <VLANs> " +
                "                     <VLANID> " +
                "                        <ID>1</ID> " +
                "                     </VLANID> " +
                "                  </VLANs> " +
                "               </VLAN> " +
                "               <VXLAN> " +
                "                  <Tunnels> " +
                "                     <Tunnel> " +
                "                        <VxlanID>7160004</VxlanID> " +
                "                        <TunnelID>1</TunnelID> " +
                "                        <FloodingProxy>false</FloodingProxy> " +
                "                     </Tunnel> " +
                "                  </Tunnels> " +
                "                  <VXLANs> " +
                "                     <Vxlan> " +
                "                        <VxlanID>7160004</VxlanID> " +
                "                        <VsiName>BD-5710YZX00023418</VsiName> " +
                "                     </Vxlan> " +
                "                  </VXLANs> " +
                "               </VXLAN> " +
                "            </top> " +
                "         </data> " +
                "      </rpc-reply> " +
                "   </env:Body> " +
                "</env:Envelope>";
        return str;

    }
}