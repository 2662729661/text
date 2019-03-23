package com.bigbigmall.xiamen.controller;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
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
        public String getMultiplicationTableXML() {

                String mt = "[";

                for (int i = 1; i <= 9; i++) {
                        mt += "[";
                        for (int j = 2; j <= 5; j++) {
                                if (j == 5) {
                                        mt += "{" + '"' + "mt1" + '"' + ":" + '"' + " " + j + " " + '"' + ","
                                                + '"' + "mt2" + '"' + ":" + '"' + i + " = " + '"' + ","
                                                + '"' + "mt3" + '"' + ":" + '"' + (i * j) + '"' + "}";
                                } else {
                                        mt += "{" + '"' + "mt1" + '"' + ":" + '"' + " " + j + " " + '"' + ","
                                                + '"' + "mt2" + '"' + ":" + '"' + i + " = " + '"' + ","
                                                + '"' + "mt3" + '"' + ":" + '"' + (i * j) + '"' + "},";
                                }
                        }
                        mt += "],";
                }

                for (int i = 1; i <= 9; i++) {
                        mt += "[";
                        for (int j = 6; j <= 9; j++) {
                                if (j == 9) {
                                        mt += "{" + '"' + "mt1" + '"' + ":" + '"' + " " + j + " " + '"' + ","
                                                + '"' + "mt2" + '"' + ":" + '"' + i + " = " + '"' + ","
                                                + '"' + "mt3" + '"' + ":" + '"' + (i * j) + '"' + "}";
                                } else {
                                        mt += "{" + '"' + "mt1" + '"' + ":" + '"' + " " + j + " " + '"' + ","
                                                + '"' + "mt2" + '"' + ":" + '"' + i + " = " + '"' + ","
                                                + '"' + "mt3" + '"' + ":" + '"' + (i * j) + '"' + "},";
                                }
                        }
                        if (i == 9) {
                                mt += "]";
                        } else {
                                mt += "],";
                        }
                }
                mt += "]";
                return mt;
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
                String string = getMultiplicationTableXML();
                JSONArray mtArray = new JSONArray(string);

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
         * 求两数区间的素数
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
         * 九九乘法表
         *
         * @return
         */
        @RequestMapping("/multiplication")
        @ResponseBody
        public String getMultiplicationTable() {

                String mt = "[";
                for (int i = 2; i <= 9; i++) {
                        for (int j = 1; j <= 9; j++) {
                                if (j == 9 && i == 9) {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + (i + "&#215;" + j + "=" + (i * j)) + '"' + "}";
                                } else if ((i == 2 || i == 3 || i == 5 || i == 7) && j == 1) {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + (i + "&#215;" + j + "=" + "<font color='red'>" + (i * j)) + "</font>" + '"' + "},";
                                } else {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + (i + "&#215;" + j + "=" + (i * j)) + '"' + "},";
                                }
                        }
                }
                mt += "]";
                return mt;
        }

        /**
         * 九九乘法表2
         *
         * @return
         */
        @RequestMapping("/multiplication2")
        @ResponseBody
        public String getTable() {
                String mt = "[";

                for (int i = 0; i <= 10; i++) {
                        mt += "[";
                        mt += "{" + '"' + i + '"' + ":" + '"' + i + '"' + "},";
                        if (i == 0) {
                                for (int j = 1; j <= 10; j++) {
                                        if (j == 10) {
                                                mt += "{" + '"' + i + '"' + ":" + '"' + j + '"' + "}";
                                        } else {
                                                mt += "{" + '"' + i + '"' + ":" + '"' + j + '"' + "},";
                                        }
                                }
                        } else {
                                for (int j = 1; j <= 10; j++) {
                                        if (j == 10) {
                                                mt += "{" + '"' + i + '"' + ":" + '"' + (i * j) + '"' + "}";
                                        } else {
                                                mt += "{" + '"' + i + '"' + ":" + '"' + (i * j) + '"' + "},";
                                        }
                                }
                        }
                        if (i == 10) {
                                mt += "]";
                        } else {
                                mt += "],";
                        }

                }

                mt += "]";
                return mt;
        }

        /**
         * 九九乘法表3
         *
         * @return
         */
        @RequestMapping("/multiplication3")
        @ResponseBody
        public String getMultiplication() {
                String mt = "[";
                for (int i = 9; i > 0; i--) {
                        mt += "[";
                        for (int j = 1; j <= i; j++) {
                                if (j == i) {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + i + "&#215;" + j + "=" + (i * j) + '"' + "}";
                                } else {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + i + "&#215;" + j + "=" + (i * j) + '"' + "},";
                                }
                        }
                        if (i == 1) {
                                mt += "]";
                        } else {
                                mt += "],";
                        }
                }
                mt += "]";
                return mt;
        }

        /**
         * 九九乘法表4
         *
         * @return
         */
        @RequestMapping("/multiplication4")
        @ResponseBody
        public String getMultiplication4() {
                String mt = "[";
                for (int i = 1; i < 10; i++) {
                        mt += "[";
                        for (int j = 1; j < 10; j++) {
                                if (j == 9) {
                                        mt += "{" + '"' + "name" + '"' + ":" + '"' + i + "*" + j + "=" + (i * j) + '"' + "}";
                                } else {
                                        mt += "{" + '"' + "name" + '"' + ":" + '"' + i + "*" + j + "=" + (i * j) + '"' + "},";
                                }
                        }
                        if (i == 9) {
                                mt += "]";
                        } else {
                                mt += "],";
                        }
                }

                mt += "]";
                return mt;
        }
}
