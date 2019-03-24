package com.bigbigmall.xiamen.controller;

import java.io.File;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author lian
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

        /**
         * 九九乘法表-xml
         *
         * @return
         */
        @RequestMapping("/multiplicationXml")
        @ResponseBody
        public JSONArray getMultiplicationTableXML() {

                JSONArray array = new JSONArray();
                for (int i = 1; i <= 9; i++) {
                        JSONArray array2 = new JSONArray();
                        array.put(array2);
                        for (int j = 2; j <= 5; j++) {
                                JSONObject object = new JSONObject();
                                object.put("mt1", " " + j + " ");
                                object.put("mt2", i + " = ");
                                object.put("mt3", (i * j));
                                array2.put(object);
                        }
                }

                for (int i = 1; i <= 9; i++) {
                        JSONArray array2 = new JSONArray();
                        array.put(array2);
                        for (int j = 6; j <= 9; j++) {
                                JSONObject object = new JSONObject();
                                object.put("mt1", " " + j + " ");
                                object.put("mt2", i + " = ");
                                object.put("mt3", (i * j));
                                array2.put(object);
                        }
                }
                return array;
        }

        /**
         * 九九乘法表-XML 获取String 转换成 XML
         *
         * @return
         */
        @RequestMapping("/tableXML")
        @ResponseBody
        public ModelAndView getMultiplicationXml(HttpServletResponse response) throws Exception {
                //获取文档对象
                DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                Document doc = newDocumentBuilder.newDocument();

                //创建根节点
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //获取数据
                JSONArray mtArray = getMultiplicationTableXML();

                //循环添加
                for (int i = 0; i < mtArray.length(); i++) {
                        Element mtsElement = doc.createElement("mts");
                        documentElement.appendChild(mtsElement);
                        JSONArray array = mtArray.getJSONArray(i);

                        for (int j = 0; j < array.length(); j++) {
                                //添加乘数
                                Element mtElement = doc.createElement("mt1" + j);
                                //添加被乘数
                                Element mt2Element = doc.createElement("mt2" + j);
                                //添加积
                                Element mt3Element = doc.createElement("mt3" + j);
                                mtsElement.appendChild(mtElement);
                                mtsElement.appendChild(mt2Element);
                                mtsElement.appendChild(mt3Element);
                                JSONObject object = array.getJSONObject(j);
                                Text mtTextNode = doc.createTextNode(object.get("mt1").toString());
                                Text mt2TextNode = doc.createTextNode(object.get("mt2").toString());
                                Text mt3TextNode = doc.createTextNode(object.get("mt3").toString());
                                mtElement.appendChild(mtTextNode);
                                mt2Element.appendChild(mt2TextNode);
                                mt3Element.appendChild(mt3TextNode);

                        }
                }

                Source source = new DOMSource(doc);

                // 将XML源文件添加到模型中，以便XsltView能够检测
                ModelAndView model = new ModelAndView("multiplication");
                model.addObject("xmlSource", source);

                return model;

                //TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(new File("C:\\netBensJmvn/a.xml")));
        }

        /**
         * 求两数区间的素数-前端页面调用
         *
         * @param minimum
         * @param maximum
         * @return
         */
        @RequestMapping("/primeNumbers")
        @ResponseBody
        public String getPrimeNumbers(int minimum, int maximum) {
                if (minimum > 0 && maximum > 0 && maximum > minimum) {

                        String max = "";
                        for (int i = minimum; i <= maximum; i++) {
                                if (i == 1) {
                                        continue;
                                }
                                if (i == 2) {
                                        max += i + "  ";
                                        continue;
                                }

                                for (int j = 2; j < i; j++) {
                                        if (i % j == 0) {
                                                break;
                                        }
                                        if ((j + 1) == i) {
                                                max += i + "  ";
                                        }
                                }
                        }
                        System.out.println("max:" + max);
                        return max;
                }
                return "Incorrect parameters";
        }

        /**
         * 九九乘法表-前端页面调用
         *
         * @return
         */
        @RequestMapping("/multiplication")
        @ResponseBody
        public String getMultiplicationTable() {

                JSONArray array = new JSONArray();
                for (int i = 2; i <= 9; i++) {
                        for (int j = 1; j <= 9; j++) {
                                JSONObject object = new JSONObject();
                                if ((i == 2 || i == 3 || i == 5 || i == 7) && j == 1) {
                                        object.put(i + "", (i + "&#215;" + j + "=" + "<font color='red'>" + (i * j) + "</font>"));
                                } else {
                                        object.put(i + "", (i + "&#215;" + j + "=" + (i * j)));
                                }
                                array.put(object);
                        }
                }
                String toString = array.toString();
                return toString;
        }

        /**
         * 九九乘法表2-前端页面调用
         *
         * @return
         */
        @RequestMapping("/multiplication2")
        @ResponseBody
        public String getTable() {
                JSONArray array = new JSONArray();
                for (int i = 0; i <= 10; i++) {
                        JSONArray array1 = new JSONArray();
                        array.put(array1);

                        JSONObject object = new JSONObject();
                        object.put(i + "", i + "");
                        array1.put(object);

                        if (i == 0) {
                                for (int j = 1; j <= 10; j++) {
                                        JSONObject object1 = new JSONObject();
                                        object1.put(i + "", j + "");
                                        array1.put(object1);
                                }
                        } else {
                                for (int j = 1; j <= 10; j++) {
                                        JSONObject object1 = new JSONObject();
                                        object1.put(i + "", (i * j) + "");
                                        array1.put(object1);
                                }
                        }
                }
                String string = array.toString();
                return string;
        }

        /**
         * 九九乘法表2-xml
         *
         * @return
         */
        @RequestMapping("/multiplicationXML2")
        @ResponseBody
        public ModelAndView getTableXML(HttpServletResponse response) throws Exception {
                //创建文档对象
                DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                Document doc = newDocumentBuilder.newDocument();

                //创建根节点
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //获取数据
                String table = getTable();
                JSONArray arrays = new JSONArray(table);

                //循环
                for (int i = 0; i < arrays.length(); i++) {
                        Element mtsElement = doc.createElement("mts");
                        documentElement.appendChild(mtsElement);
                        JSONArray array = arrays.getJSONArray(i);

                        for (int j = 0; j < array.length(); j++) {
                                Element mtElement = doc.createElement("mt" + j);
                                mtsElement.appendChild(mtElement);
                                JSONObject object = array.getJSONObject(j);
                                Text mtTextNode = doc.createTextNode(object.get(i + "").toString());
                                mtElement.appendChild(mtTextNode);
                        }

                }

                Source source = new DOMSource(doc);

                // 将XML源文件添加到模型中，以便XsltView能够检测
                ModelAndView model = new ModelAndView("multiplication2");
                model.addObject("xmlSource", source);

                return model;
                //TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));
        }

        /**
         * 九九乘法表3-前端页面调用
         *
         * @return
         */
        @RequestMapping("/multiplication3")
        @ResponseBody
        public String getMultiplication() {

                JSONArray array = new JSONArray();
                for (int i = 99; i > 0; i--) {
                        JSONArray array1 = new JSONArray();
                        array.put(array1);
                        for (int j = 1; j <= i; j++) {
                                JSONObject object = new JSONObject();
                                object.put(i + "", i + "&#215;" + j + "=" + (i * j));
                                array1.put(object);
                        }
                }
                String string = array.toString();
                return string;
        }

        /**
         * 九九乘法表3-xml-controller调用
         *
         * @return
         */
        @RequestMapping("/multiplication3XML")
        @ResponseBody
        public JSONArray getMultiplicationXML() {
                JSONArray array = new JSONArray();
                for (int i = 9; i > 0; i--) {
                        JSONArray array1 = new JSONArray();
                        array.put(array1);
                        for (int j = 1; j <= i; j++) {
                                JSONObject object = new JSONObject();
                                object.put("mt1", i + " ");
                                object.put("mt2", j + " = ");
                                object.put("mt3", (i * j));
                                array1.put(object);
                        }
                }
                return array;
        }

        /**
         * 九九乘法表3-XML
         *
         * @return
         */
        @RequestMapping("/multiplicationXML3")
        @ResponseBody
        public ModelAndView getMultiplicationXML(HttpServletResponse response) throws Exception {
                //获取文档对象
                DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                Document doc = newDocumentBuilder.newDocument();

                //创建根节点
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //获取Array
                JSONArray multiplicationArray = getMultiplicationXML();
                int s = 1;
                //循环
                for (int i = multiplicationArray.length() - 1; i >= 0; i--) {
                        Element mtsElement = doc.createElement("mts");
                        documentElement.appendChild(mtsElement);
                        JSONArray mtsArray = multiplicationArray.getJSONArray(i);

                        for (int j = 0; j < 9; j++) {
                                Element mtElement = doc.createElement("mt" + j);
                                Element mt2Element = doc.createElement("mt1" + j);
                                Element mt3Element = doc.createElement("mt2" + j);
                                mtsElement.appendChild(mtElement);
                                mtsElement.appendChild(mt2Element);
                                mtsElement.appendChild(mt3Element);
                                if (s > j) {
                                        JSONObject mtObject = mtsArray.getJSONObject(j);
                                        Text mt1TextNode = doc.createTextNode(mtObject.get("mt1").toString());
                                        Text mt2TextNode = doc.createTextNode(mtObject.get("mt2").toString());
                                        Text mt3TextNode = doc.createTextNode(mtObject.get("mt3").toString());
                                        mtElement.appendChild(mt1TextNode);
                                        mt2Element.appendChild(mt2TextNode);
                                        mt3Element.appendChild(mt3TextNode);
                                }
                        }
                        s++;
                }
                Source source = new DOMSource(doc);
//
                // 将XML源文件添加到模型中，以便XsltView能够检测
                ModelAndView model = new ModelAndView("multiplication3");
                model.addObject("xmlSource", source);

                return model;
                //TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));
        }

        /**
         * 九九乘法表4
         *
         * @return
         */
        @RequestMapping("/multiplication4")
        @ResponseBody
        public String getMultiplication4() {
                JSONArray array = new JSONArray();
                for (int i = 1; i < 10; i++) {
                        JSONArray array1 = new JSONArray();
                        array.put(array1);
                        for (int j = 1; j < 10; j++) {
                                JSONObject object = new JSONObject();
                                object.put("name", i + "*" + j + "=" + (i * j));
                                array1.put(object);
                        }
                }

                String string = array.toString();
                return string;
        }
}
