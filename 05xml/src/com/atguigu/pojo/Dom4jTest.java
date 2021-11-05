package com.atguigu.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class Dom4jTest {
    @Test
    public void test1() throws Exception {
        // 创建一个SaxReader输入流，去读取 xml配置文件，生成Document对象
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read("src/books.xml");

        System.out.println(document);
    }

    /*
    读取books.xml文件生成Book类
     */
    @Test
    public void test2() throws Exception {
        //1.读取books.xml文件
        SAXReader reader = new SAXReader();
        Document document = reader.read("src/books.xml");
        //2.通过Document对象获取根元素
        Element rootElement = document.getRootElement();
        //3.通过根元素获取book标签对象
        List<Element> books = rootElement.elements("book");
        //4.遍历，处理每个book标签转化为Book类
        for(Element book : books){
            //asXML():把标签对象转换为标签字符串
//            System.out.println(book.asXML());

            //方式一：通过继续element()查找子标签然后通过getText()获取其内容
            Element nameElement = book.element("name");
            //getText():可以获取标签中的文本内容
            String nameText = nameElement.getText();

            //方式二：直接获取指定标签名的内容---elementText()
            String priceText = book.elementText("price");
            String authorText = book.elementText("author");

            //获取<book>标签的sn属性
            String snValue = book.attributeValue("sn");

            System.out.println(new Book(snValue,nameText,Double.parseDouble(priceText),authorText));
        }
    }

}
