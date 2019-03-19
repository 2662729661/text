package com.bigbigmall.xiamen.controller;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
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
import org.w3c.dom.Text;

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
        void get(HttpServletResponse response) throws Exception {
                //获取文档对象
                DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                Document doc = newDocumentBuilder.newDocument();

                //创建根节点
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);
        }

        /**
         * https://redan-api.herokuapp.com/story/
         *
         * @param response
         * @throws Exception
         */
        @RequestMapping("/story")
        @ResponseBody
        void getStory(HttpServletResponse response) throws Exception {
                //创建文档对象
                DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                Document doc = newDocumentBuilder.newDocument();

                //添加根节点
                Element documentElement = doc.createElement("document");
                doc.appendChild(documentElement);

                //获取请求体
                HttpGet httpGet = new HttpGet("https://redan-api.herokuapp.com/story/");
                CloseableHttpResponse execute = HttpClients.createDefault().execute(httpGet);
                HttpEntity entity = execute.getEntity();

                if (entity != null) {
                        String all = EntityUtils.toString(entity, "UTF-8");

                        //添加status属性
                        String statusValue = new JSONObject(all).get("status").toString();
                        documentElement.setAttribute("status", statusValue);

                        /*---------------------------获取result的值---------------------------------*/
                        //获取result值Object
                        JSONArray resultArray = new JSONObject(all).getJSONArray("result");
                        JSONObject resultObject = resultArray.getJSONObject(0);


                        /*--------------------------添加节点comments----------------------------------*/
                        Element commentsElement = doc.createElement("comments");
                        documentElement.appendChild(commentsElement);
                        JSONArray commentsArray = resultObject.getJSONArray("comments");
                        for (int i = 0; i < commentsArray.length(); i++) {
                                JSONObject jsonObject = commentsArray.getJSONObject(i);
                        }


                        /*--------------------------添加节点emotions----------------------------------*/
                        //创建result的Text-emotions
                        Element emotionsElement = doc.createElement("emotions");
                        Text emotionsTextNode = doc.createTextNode(resultObject.get("emotions").toString());
                        emotionsElement.appendChild(emotionsTextNode);
                        documentElement.appendChild(emotionsElement);

                        /*---------------------------添加节点postedAt---------------------------------*/
                        //创建result的Text-postedAt
                        Element postedAtElement = doc.createElement("postedAt");
                        Text postedAtTextNode = doc.createTextNode(resultObject.get("postedAt").toString());
                        postedAtElement.appendChild(postedAtTextNode);
                        documentElement.appendChild(postedAtElement);

                        /*-----------------------------添加节点author-------------------------------*/
                        //创建result的Object-author
                        Element authorElement = doc.createElement("author");
                        documentElement.appendChild(authorElement);
                        JSONObject authorObject = resultObject.getJSONObject("author");
                        //创建author的Text-nickname
                        Element authorNicknameElement = doc.createElement("nickname");
                        Text authorNicknameTextNode = doc.createTextNode(authorObject.get("nickname").toString());
                        authorNicknameElement.appendChild(authorNicknameTextNode);
                        authorElement.appendChild(authorNicknameElement);
                        //创建author的Text-id
                        Element authorIdElement = doc.createElement("id");
                        Text authorIdTextNode = doc.createTextNode(authorObject.get("id").toString());
                        authorIdElement.appendChild(authorIdTextNode);
                        authorElement.appendChild(authorIdElement);

                        /*-----------------------------添加节点id-------------------------------*/
                        //创建result的Text-id
                        Element idElement = doc.createElement("id");
                        Text idTextNode = doc.createTextNode(resultObject.get("id").toString());
                        idElement.appendChild(idTextNode);
                        documentElement.appendChild(idElement);

                        /*------------------------------添加节点content------------------------------*/
                        //创建result的Text-content
                        Element contentElement = doc.createElement("content");
                        Text contentTextNode = doc.createTextNode(resultObject.get("content").toString());
                        contentElement.appendChild(contentTextNode);
                        documentElement.appendChild(contentElement);
                }

                //输出到页面
                TransformerFactory newInstance1 = TransformerFactory.newInstance();
                Transformer newTransformer = newInstance1.newTransformer();
                newTransformer.transform(new DOMSource(doc),
                        new StreamResult(response.getOutputStream()));

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

                //获取响应数据
                HttpGet httpget = new HttpGet("https://redan-api.herokuapp.com/storyEmotions");
                CloseableHttpResponse response1 = HttpClients.createDefault().execute(httpget);
                HttpEntity entity1 = response1.getEntity();

                if (entity1 != null) {

                        //获取数据对象
                        String retSrc = EntityUtils.toString(entity1, "UTF-8");
                        JSONObject all = new JSONObject(retSrc);

                        //第一层-embedded
                        Element embeddedElement = doc.createElement("_embedded");
                        documentElement.appendChild(embeddedElement);

                        //第一层-links
                        Element linksElement = doc.createElement("_links");
                        documentElement.appendChild(linksElement);
                        JSONObject linksObject = all.getJSONObject("_links");
                        //第二层-self
                        Element selfElement = doc.createElement("self");
                        linksElement.appendChild(selfElement);
                        JSONObject selfObject = linksObject.getJSONObject("self");
                        //第三层-href
                        Element hrefElement = doc.createElement("href");
                        selfElement.appendChild(hrefElement);
                        Text hrefTextNode = doc.createTextNode(selfObject.getString("href"));
                        hrefElement.appendChild(hrefTextNode);
                        //第三层-templated
                        Element templatedElement = doc.createElement("templated");
                        selfElement.appendChild(templatedElement);
                        Text templatedTextNode = doc.createTextNode(selfObject.getString("templated"));
                        hrefElement.appendChild(templatedTextNode);

                        //第一层-page
                        Element pageElement = doc.createElement("page");
                        documentElement.appendChild(pageElement);
                        JSONObject jsonObject = all.getJSONObject("page");
                        //第二层
                        Element sizeElement = doc.createElement("size");
                        pageElement.appendChild(sizeElement);
                        Element totalElementsElement = doc.createElement("totalElements");
                        pageElement.appendChild(totalElementsElement);
                        Element totalPagesElement = doc.createElement("totalPages");
                        pageElement.appendChild(totalPagesElement);
                        Element numberElement = doc.createElement("number");
                        pageElement.appendChild(numberElement);

                }
                //输出到页面
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));

        }
}
