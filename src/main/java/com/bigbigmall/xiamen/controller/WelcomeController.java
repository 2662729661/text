package com.bigbigmall.xiamen.controller;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author lian
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

        /**
         * https://redan-api.herokuapp.com/profile/personnels
         *
         * @param response
         * @throws Exception
         */
        @RequestMapping("/")
        @ResponseBody
        void getPersonnels(HttpServletResponse response) throws Exception {
                //创建文档对象
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                //创建根标签
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //创建连接
                HttpGet httpGet = new HttpGet("https://redan-api.herokuapp.com/profile/personnels");

                //获取请求
                CloseableHttpResponse execute = HttpClients.createDefault().execute(httpGet);

                //获取请求体
                HttpEntity entity = execute.getEntity();

                if (entity != null) {
                        //设置字符集
                        String toString = EntityUtils.toString(entity, "UTF-8");

                        //获取Object
                        JSONObject jsonObject = new JSONObject(toString).getJSONObject("alps");

                        //获取keys
                        Iterator<String> keys = jsonObject.keys();

                        //循环-alps
                        for (int i = 0; i < jsonObject.length(); i++) {
                                //获取key
                                String next = keys.next();

                                //判断是Array还是attribute
                                if (next.equals("version")) {
                                        //添加Attribute
                                        documentElement.setAttribute(next, jsonObject.get(next).toString());

                                } else if (next.equals("descriptor")) {

                                        //获取Array
                                        JSONArray jsonArray = jsonObject.getJSONArray(next);
                                        //循环Array
                                        for (int j = 0; j < jsonArray.length(); j++) {
                                                //创建Label
                                                Element nextElement = doc.createElement(next);
                                                documentElement.appendChild(nextElement);

                                                //获取Object
                                                JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                                                //获取keys
                                                Iterator<String> keys1 = jsonObject1.keys();

                                                //循环
                                                for (int k = 0; k < jsonObject1.length(); k++) {
                                                        //获取key
                                                        String next1 = keys1.next();
                                                        //判断是否是Attribute
                                                        if (next1.equals("id") || next1.equals("name")) {
                                                                //添加Attribute
                                                                nextElement.setAttribute(next1, jsonObject1.get(next1).toString());
                                                        }

                                                        //判断是Text
                                                        if (!next1.equals("id") && !next1.equals("name") && !next1.equals("descriptor")) {
                                                                //创建Label
                                                                Element next1Element = doc.createElement(next1);
                                                                //添加Text
                                                                next1Element.setTextContent(jsonObject1.get(next1).toString());
                                                                //添加Label
                                                                nextElement.appendChild(next1Element);
                                                        }

                                                        //判断是Array
                                                        if (next1.equals("descriptor")) {
                                                                //获取Array
                                                                JSONArray jsonArray1 = jsonObject1.getJSONArray(next1);

                                                                //循环
                                                                for (int q = 0; q < jsonArray1.length(); q++) {
                                                                        //创建Label
                                                                        Element next1Element = doc.createElement(next1);
                                                                        nextElement.appendChild(next1Element);
                                                                        //获取Object
                                                                        JSONObject jsonObject2 = jsonArray1.getJSONObject(q);
                                                                        //获取keys
                                                                        Iterator<String> keys2 = jsonObject2.keys();

                                                                        //循环
                                                                        for (int w = 0; w < jsonObject2.length(); w++) {
                                                                                //获取key
                                                                                String next2 = keys2.next();

                                                                                //判断Attribute
                                                                                if (next2.equals("name")) {
                                                                                        //添加Attribute
                                                                                        next1Element.setAttribute(next2, jsonObject2.get(next2).toString());
                                                                                }

                                                                                //判断Text
                                                                                if (!next2.equals("name") && !next2.equals("doc")) {
                                                                                        //创建Label
                                                                                        Element next2Element = doc.createElement(next2);
                                                                                        //添加Text
                                                                                        next2Element.setTextContent(jsonObject2.get(next2).toString());
                                                                                        //添加Label
                                                                                        next1Element.appendChild(next2Element);
                                                                                }

                                                                                //判断Object
                                                                                if (next2.equals("doc")) {
                                                                                        //创建Label
                                                                                        Element next2Element = doc.createElement(next2);
                                                                                        next1Element.appendChild(next2Element);
                                                                                        //获取Object
                                                                                        JSONObject jsonObject3 = jsonObject2.getJSONObject(next2);
                                                                                        //获取keys
                                                                                        Iterator<String> keys3 = jsonObject3.keys();

                                                                                        //循环
                                                                                        for (int e = 0; e < jsonObject3.length(); e++) {
                                                                                                //获取key
                                                                                                String next3 = keys3.next();
                                                                                                //创建Label
                                                                                                Element next3Element = doc.createElement(next3);
                                                                                                //添加Text
                                                                                                next3Element.setTextContent(jsonObject3.get(next3).toString());
                                                                                                //添加Label
                                                                                                next2Element.appendChild(next3Element);
                                                                                        }
                                                                                }

                                                                        }
                                                                }
                                                        }
                                                }
                                        }

                                }

                        }
                }

                //输出到页面
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));
        }

        /**
         * https://redan-api.herokuapp.com/profile/storyEmotions
         *
         * @param response
         * @throws Exception
         */
        @RequestMapping("/profileStoryEmotions")
        @ResponseBody
        void getProfileStoryEmotions(HttpServletResponse response) throws Exception {
                //创建文档对象
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                //创建根标签
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //创建连接
                HttpGet httpGet = new HttpGet("https://redan-api.herokuapp.com/profile/storyEmotions");

                //获取响应
                CloseableHttpResponse execute = HttpClients.createDefault().execute(httpGet);

                //获取响应体
                HttpEntity entity = execute.getEntity();

                if (entity != null) {

                        //设置字符集
                        String toString = EntityUtils.toString(entity, "UTF-8");

                        //获取对象
                        JSONObject jsonObject = new JSONObject(toString).getJSONObject("alps");

                        //创建标签
                        Element createElement = doc.createElement("alps");
                        documentElement.appendChild(createElement);

                        //获取keys
                        Iterator<String> keys = jsonObject.keys();

                        //循环
                        for (int i = 0; i < jsonObject.length(); i++) {
                                //获取key
                                String next = keys.next();

                                //判断是属性
                                if (next.equals("version")) {
                                        //添加属性
                                        createElement.setAttribute(next, jsonObject.get(next).toString());
                                }

                                //判断是数组-descriptor
                                if (next.equals("descriptor")) {
                                        //获取数组
                                        JSONArray jsonArray = jsonObject.getJSONArray(next);

                                        //循环数组-descriptor1
                                        for (int j = 0; j < jsonArray.length(); j++) {
                                                //创建标签
                                                Element nextElement1 = doc.createElement(next);
                                                createElement.appendChild(nextElement1);
                                                //获取对象
                                                JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                                                //获取对象keys
                                                Iterator<String> keys1 = jsonObject1.keys();

                                                //循环
                                                for (int k = 0; k < jsonObject1.length(); k++) {
                                                        //获取key
                                                        String next1 = keys1.next();

                                                        //判断是属性
                                                        if (next1.equals("id") || next1.equals("name") || next1.equals("type")) {
                                                                //添加属性
                                                                nextElement1.setAttribute(next1, jsonObject1.get(next1).toString());
                                                        }

                                                        //判断是文本
                                                        if (next1.equals("rt")) {
                                                                //创建标签
                                                                Element next1Element1 = doc.createElement(next1);
                                                                //添加文本
                                                                next1Element1.setTextContent(jsonObject1.get(next1).toString());
                                                                //添加标签
                                                                nextElement1.appendChild(next1Element1);
                                                        }

                                                        //判断是数组
                                                        if (next1.equals("descriptor")) {
                                                                //获取数组
                                                                JSONArray jsonArray1 = jsonObject1.getJSONArray(next1);

                                                                //循环数组
                                                                for (int q = 0; q < jsonArray1.length(); q++) {
                                                                        //创建标签
                                                                        Element next1Element1 = doc.createElement(next1);
                                                                        nextElement1.appendChild(next1Element1);
                                                                        //获取对象
                                                                        JSONObject jsonObject2 = jsonArray1.getJSONObject(q);
                                                                        //获取keys
                                                                        Iterator<String> keys2 = jsonObject2.keys();

                                                                        //循环
                                                                        for (int w = 0; w < jsonObject2.length(); w++) {
                                                                                //获取key
                                                                                String next2 = keys2.next();

                                                                                //判断是属性
                                                                                if (next2.equals("name") || next2.equals("type")) {
                                                                                        //添加属性
                                                                                        next1Element1.setAttribute(next2, jsonObject2.get(next2).toString());
                                                                                }

                                                                                //判断是文本
                                                                                if (next2.equals("rt")) {
                                                                                        //创建标签
                                                                                        Element next2Element1 = doc.createElement(next2);
                                                                                        //添加文本
                                                                                        next2Element1.setTextContent(jsonObject2.get(next2).toString());
                                                                                        //添加标签
                                                                                        next1Element1.appendChild(next2Element1);
                                                                                }

                                                                                //判断是对象
                                                                                if (next2.equals("doc")) {
                                                                                        //创建标签
                                                                                        Element next2Element1 = doc.createElement(next2);
                                                                                        next1Element1.appendChild(next2Element1);
                                                                                        //获取对象
                                                                                        JSONObject jsonObject3 = jsonObject2.getJSONObject(next2);
                                                                                        //获取keys
                                                                                        Iterator<String> keys3 = jsonObject3.keys();

                                                                                        //循环
                                                                                        for (int e = 0; e < jsonObject3.length(); e++) {
                                                                                                //获取key
                                                                                                String next3 = keys3.next();

                                                                                                //判断是属性
                                                                                                if (next3.equals("format")) {
                                                                                                        //添加属性
                                                                                                        next2Element1.setAttribute(next3, jsonObject3.get(next3).toString());
                                                                                                }

                                                                                                //判断是文本
                                                                                                if (next3.equals("value")) {
                                                                                                        //创建标签
                                                                                                        Element next3Element1 = doc.createElement(next3);
                                                                                                        //添加文本
                                                                                                        next3Element1.setTextContent(jsonObject3.get(next3).toString());
                                                                                                        //添加标签
                                                                                                        next2Element1.appendChild(next3Element1);
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }

                }

                //输出到页面
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));

        }

        /**
         * https://redan-api.herokuapp.com/stories
         *
         * @param response
         * @throws Exception
         */
        @RequestMapping("/stories")
        @ResponseBody
        void getStories(HttpServletResponse response) throws Exception {
                //获取文档对象
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                //创建根标签
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //创建请求方法        
                HttpGet httpGet = new HttpGet("https://redan-api.herokuapp.com/stories");

                //获取响应对象
                CloseableHttpResponse execute = HttpClients.createDefault().execute(httpGet);

                //获取响应体
                HttpEntity entity = execute.getEntity();

                if (entity != null) {
                        //设置编码字符集
                        String toString = EntityUtils.toString(entity, "UTF-8");

                        //获取数据
                        JSONObject jsonObject = new JSONObject(toString);

                        //获取keys
                        Iterator<String> keys = jsonObject.keys();

                        //循环对象
                        for (int i = 0; i < jsonObject.length(); i++) {
                                //获取key
                                String next = keys.next();
                                //创建标签
                                Element nextElement = doc.createElement(next);
                                documentElement.appendChild(nextElement);

                                //获取对象
                                JSONObject jsonObject1 = jsonObject.getJSONObject(next);
                                //获取keys
                                Iterator<String> keys1 = jsonObject1.keys();

                                //循环
                                for (int j = 0; j < jsonObject1.length(); j++) {
                                        //获取key
                                        String next1 = keys1.next();

                                        //创建标签
                                        Element next1Element = doc.createElement(next1);
                                        nextElement.appendChild(next1Element);

                                        //判断是否是文本
                                        if (next.equals("page")) {
                                                //添加文本
                                                next1Element.setTextContent(jsonObject1.get(next1).toString());
                                        }

                                        //判断是否是对象
                                        if (next.equals("_links")) {
                                                //获取对象
                                                JSONObject jsonObject2 = jsonObject1.getJSONObject(next1);
                                                //获取keys
                                                Iterator<String> keys2 = jsonObject2.keys();

                                                //循环
                                                for (int k = 0; k < jsonObject2.length(); k++) {
                                                        //获取key
                                                        String next2 = keys2.next();
                                                        //创建标签
                                                        Element next2Element = doc.createElement(next2);
                                                        //添加文本
                                                        next2Element.setTextContent(jsonObject2.get(next2).toString());
                                                        //添加标签
                                                        next1Element.appendChild(next2Element);
                                                }
                                        }

                                        //判断是否是数组
                                        if (next.equals("_embedded")) {
                                                //获取数组
                                                JSONArray jsonArray = jsonObject1.getJSONArray(next1);
                                                //获取对象
                                                JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                                                //获取所有keys
                                                Iterator<String> keys2 = jsonObject2.keys();

                                                //循环
                                                for (int k = 0; k < jsonObject2.length(); k++) {
                                                        //获取key
                                                        String next2 = keys2.next();
                                                        //创建标签
                                                        Element next2Element = doc.createElement(next2);
                                                        //判断是文本
                                                        if (!next2.equals("_links")) {
                                                                next2Element.setTextContent(jsonObject2.get(next2).toString());
                                                        }
                                                        next1Element.appendChild(next2Element);

                                                        //判断是对象
                                                        if (next2.equals("_links")) {
                                                                //获取对象
                                                                JSONObject jsonObject3 = jsonObject2.getJSONObject(next2);
                                                                //获取keys
                                                                Iterator<String> keys3 = jsonObject3.keys();

                                                                //循环
                                                                for (int q = 0; q < jsonObject3.length(); q++) {
                                                                        //获取key
                                                                        String next3 = keys3.next();
                                                                        //创建标签
                                                                        Element next3Element = doc.createElement(next3);
                                                                        next2Element.appendChild(next3Element);
                                                                        //获取对象
                                                                        JSONObject jsonObject4 = jsonObject3.getJSONObject(next3);

                                                                        //获取对象key
                                                                        String next4 = jsonObject4.keys().next();
                                                                        //创建标签
                                                                        Element next4Element = doc.createElement(next4);
                                                                        //添加文本
                                                                        next4Element.setTextContent(jsonObject4.get(next4).toString());
                                                                        //添加标签
                                                                        next3Element.appendChild(next4Element);
                                                                }

                                                        }
                                                }
                                        }
                                }

                        }

                }
                //输出到页面
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));

        }

        /**
         * https://redan-api.herokuapp.com/profile/emotions
         *
         * @param response
         * @throws Exception
         */
        @RequestMapping("/emotions")
        @ResponseBody
        void getEmotions(HttpServletResponse response) throws Exception {

                //获取文档对象
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();

                //创建根标签
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //创建请求方法
                HttpGet httpget = new HttpGet("https://redan-api.herokuapp.com/profile/emotions");

                //获取响应对象
                CloseableHttpResponse response1 = HttpClients.createDefault().execute(httpget);
                //获取响应体
                HttpEntity entity1 = response1.getEntity();
                if (entity1 != null) {
                        //设置编码字符集
                        String retSrc = EntityUtils.toString(entity1, "UTF-8");

                        //获取数据
                        JSONObject jsonObject = new JSONObject(retSrc);
                        //获取key
                        String next = jsonObject.keys().next();

                        //创建标签
                        Element nextElement = doc.createElement(next);
                        //添加标签
                        documentElement.appendChild(nextElement);

                        //获取对象
                        JSONObject jsonObject1 = jsonObject.getJSONObject(next);
                        //获取keys
                        Iterator<String> keys = jsonObject1.keys();

                        //循环获取-alps
                        for (int i = 0; i < jsonObject1.length(); i++) {
                                //获取key
                                String next1 = keys.next();

                                //添加属性
                                if (next1.equals("version")) {
                                        nextElement.setAttribute(next1, jsonObject1.get(next1).toString());
                                }

                                //获取数组
                                if (next1.equals("descriptor")) {

                                        //获取数组
                                        JSONArray jsonArray = jsonObject1.getJSONArray(next1);

                                        //循环数组-descriptor
                                        for (int j = 0; j < jsonArray.length(); j++) {
                                                //获取对象
                                                JSONObject jsonObject2 = jsonArray.getJSONObject(j);
                                                Element next2Element = doc.createElement(next1);
                                                nextElement.appendChild(next2Element);
                                                //获取对象所有keys
                                                Iterator<String> keys1 = jsonObject2.keys();

                                                //循环所有对象
                                                for (int k = 0; k < jsonObject2.length(); k++) {
                                                        //获取key
                                                        String next2 = keys1.next();

                                                        if (!next2.equals("descriptor")) {
                                                                //创建标签
                                                                Element next3Element = doc.createElement(next2);
                                                                //添加文本
                                                                next3Element.setTextContent(jsonObject2.get(next2).toString());
                                                                //添加标签
                                                                next2Element.appendChild(next3Element);
                                                        }

                                                        //判断是否是数组
                                                        if (next2.equals("descriptor")) {
                                                                //获取数组
                                                                JSONArray jsonArray1 = jsonObject2.getJSONArray(next2);

                                                                //循环数组
                                                                for (int q = 0; q < jsonArray1.length(); q++) {
                                                                        //获取对象
                                                                        JSONObject jsonObject3 = jsonArray1.getJSONObject(q);
                                                                        //创建标签
                                                                        Element next3Element = doc.createElement(next2);
                                                                        //添加标签
                                                                        next2Element.appendChild(next3Element);
                                                                        //获取keys
                                                                        Iterator<String> keys2 = jsonObject3.keys();

                                                                        //循环-descriptor
                                                                        for (int w = 0; w < jsonObject3.length(); w++) {
                                                                                //获取key
                                                                                String next4 = keys2.next();
                                                                                //创建标签
                                                                                Element next4Element = doc.createElement(next4);
                                                                                //判断是否是文本
                                                                                if (!next4.equals("doc")) {
                                                                                        //添加文本
                                                                                        next4Element.setTextContent(jsonObject3.get(next4).toString());
                                                                                }
                                                                                //添加标签
                                                                                next3Element.appendChild(next4Element);

                                                                                //判断是否是对象
                                                                                if (next4.equals("doc")) {
                                                                                        //获取对象
                                                                                        JSONObject jsonObject4 = jsonObject3.getJSONObject(next4);
                                                                                        //获取keys
                                                                                        Iterator<String> keys5 = jsonObject4.keys();
                                                                                        //循环-doc
                                                                                        for (int e = 0; e < jsonObject4.length(); e++) {
                                                                                                //获取key
                                                                                                String next5 = keys5.next();
                                                                                                //创建标签
                                                                                                Element next5Element = doc.createElement(next5);
                                                                                                //添加文本
                                                                                                next5Element.setTextContent(jsonObject4.get(next5).toString());
                                                                                                //添加标签
                                                                                                next4Element.appendChild(next5Element);
                                                                                        }

                                                                                }
                                                                        }
                                                                }

                                                        }

                                                }
                                        }

                                }
                        }

                }
                //输出到页面
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));

        }

        /**
         * https://redan-api.herokuapp.com/storyEmotions
         *
         * @param response
         * @throws Exception
         */
        @RequestMapping("/storyEmotions")
        @ResponseBody
        void getStoryEmotions(HttpServletResponse response) throws Exception {
                //获取文档对象
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();

                //创建根标签
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //创建请求方法
                HttpGet httpget = new HttpGet("https://redan-api.herokuapp.com/storyEmotions");

                //获取响应对象
                CloseableHttpResponse response1 = HttpClients.createDefault().execute(httpget);
                //获取响应体
                HttpEntity entity1 = response1.getEntity();
                if (entity1 != null) {
                        //设置编码字符集
                        String retSrc = EntityUtils.toString(entity1, "UTF-8");

                        //获取所有对象
                        JSONObject jsonObject = new JSONObject(retSrc);

                        //获取keys
                        Iterator<String> keys = jsonObject.keys();

                        //循环-1
                        for (int i = 0; i < jsonObject.length(); i++) {
                                //获取key
                                String next = keys.next();

                                //创建标签
                                Element nextElement = doc.createElement(next);

                                //添加标签
                                documentElement.appendChild(nextElement);

                                //获取对象
                                JSONObject jsonObject1 = jsonObject.getJSONObject(next);

                                //获取keys1
                                Iterator<String> keys1 = jsonObject1.keys();

                                //循环-2
                                for (int j = 0; j < jsonObject1.length(); j++) {
                                        //获取key
                                        String next1 = keys1.next();

                                        //创建标签
                                        Element next1Element = doc.createElement(next1);

                                        //判断值是文本
                                        if (!(jsonObject1.get(next1) instanceof JSONObject) && !(jsonObject1.get(next1) instanceof JSONArray)) {
                                                //添加文本
                                                next1Element.setTextContent(jsonObject1.get(next1).toString());
                                        }

                                        //添加标签
                                        nextElement.appendChild(next1Element);

                                        //判断是对象
                                        if (jsonObject1.get(next1) instanceof JSONObject) {
                                                //获取对象
                                                JSONObject jsonObject2 = jsonObject1.getJSONObject(next1);

                                                //获取对象所有keys
                                                Iterator<String> keys2 = jsonObject2.keys();

                                                //循环-3
                                                for (int k = 0; k < jsonObject2.length(); k++) {
                                                        //获取key
                                                        String next2 = keys2.next();

                                                        //创建标签-添加标签
                                                        Element next2Element = doc.createElement(next2);

                                                        //添加文本
                                                        next2Element.setTextContent(jsonObject2.get(next2).toString());

                                                        //添加标签
                                                        next1Element.appendChild(next2Element);

                                                }
                                        }
                                }
                        }
                }
                //输出到页面
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));

        }

        @RequestMapping("/index")
        @ResponseBody
        void index(HttpServletResponse response) throws Exception {
                //创建文档
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                //创建根节点
                Element documentElement = document.createElement("document");
                documentElement.setAttribute("user", "me");
                document.appendChild(documentElement);

                //创建stories标签
                Element stories = document.createElement("stories");
                stories.setAttribute("status", "200");
                documentElement.appendChild(stories);

                /*---------------------------创建story标签---------------------------------*/
                //创建story标签
                Element story = document.createElement("story");
                story.setAttribute("id", "190cc757-7a0e-4712-97c9-bf5f77fd5a8e");
                story.setAttribute("emotions", "0");
                story.setAttribute("postedAt", "2014-02-11T00:00:00+08:00");
                stories.appendChild(story);

                //创建comments标签
                Element comments = document.createElement("comments");
                story.appendChild(comments);
                //创建comment标签
                Element comment = document.createElement("comment");
                comment.setAttribute("id", "defad05a-0eb1-4545-b4aa-39460f174fbc");
                comments.appendChild(comment);
                //创建comment标签下content标签
                Element commentContent = document.createElement("content");
                commentContent.setTextContent("這是一篇好文章阿");
                comment.appendChild(commentContent);
                //创建comment标签下who标签
                Element commentWho = document.createElement("who");
                commentWho.setAttribute("id", "7f84cc84-bb50-4cdf-8578-ef2827d35726");
                commentWho.setAttribute("nickname", "pc");
                comment.appendChild(commentWho);
                //创建comment标签
                Element comment2 = document.createElement("comment");
                comment2.setAttribute("id", "8ee5d256-a4e9-4c67-a5d0-0699d9b0cbd7");
                comments.appendChild(comment2);
                //创建comment标签下content标签
                Element comment2Content = document.createElement("content");
                comment2Content.setTextContent("家惠什麼時候要開始減肥呀");
                comment2.appendChild(comment2Content);
                //创建comment标签下who标签
                Element comment2Who = document.createElement("who");
                comment2Who.setAttribute("id", "7f84cc84-bb50-4cdf-8578-ef2827d35726");
                comment2Who.setAttribute("nickname", "pc");
                comment2.appendChild(comment2Who);

                //创建author标签
                Element author = document.createElement("author");
                author.setAttribute("id", "e4b6a337-6647-4521-ac9a-c4e0a3853626");
                author.setAttribute("nickname", "redan");
                story.appendChild(author);

                //创建content标签
                Element content = document.createElement("content");
                content.setTextContent("this is content 這是一篇文章");
                story.appendChild(content);

                /*--------------------------创建story2标签--------------------------------*/
                //创建story2标签
                Element story2 = document.createElement("story");
                story2.setAttribute("id", "72ff2337-1b0b-473c-8120-6ff028632806");
                story2.setAttribute("emotions", "0");
                story2.setAttribute("postedAt", "2019-01-30 03:08:30.556067");
                stories.appendChild(story2);

                //创建author标签
                Element story2Author = document.createElement("author");
                story2Author.setAttribute("id", "fdb53388-243b-11e9-b2d7-23d188ab349e");
                story2Author.setAttribute("nickname", "阿惠");
                story2.appendChild(story2Author);

                //创建content标签
                Element story2Content = document.createElement("content");
                story2Content.setTextContent("路，就是一條直直的。認同請按讚，不認同請分享");
                story2.appendChild(story2Content);

                /*---------------------------创建story3标签---------------------------------*/
                //创建story3标签
                Element story3 = document.createElement("story");
                story3.setAttribute("id", "5bc82c97-14e7-44ad-91ea-8941a12eae43");
                story3.setAttribute("emotions", "0");
                story3.setAttribute("postedAt", "2019-01-30 03:08:54.812136");
                stories.appendChild(story3);

                //创建author标签
                Element story3Author = document.createElement("author");
                story3Author.setAttribute("id", "f45f0aa2-243b-11e9-b2d7-7339b94259f1");
                story3Author.setAttribute("nickname", "生仔");
                story3.appendChild(story3Author);

                //创建content标签
                Element story3Content = document.createElement("content");
                story3Content.setTextContent("累、累、累！");
                story3.appendChild(story3Content);

                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(response.getOutputStream()));
        }

}
