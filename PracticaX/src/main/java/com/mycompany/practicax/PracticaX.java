/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practicax;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.Scanner;
public class PracticaX {

    public static void main(String[] args) {
        
      try {
            // Cargar el archivo XML
            File inputFile = new File("Libros.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);

            // Pedir al usuario los datos de un nuevo libro
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el título del nuevo libro: ");
            String titulo = scanner.nextLine();
            System.out.print("Ingrese el autor del nuevo libro: ");
            String autor = scanner.nextLine();
            System.out.print("Ingrese la fecha de publicación del nuevo libro: ");
            String fecha = scanner.nextLine();

            // Crear un nuevo libro
            Element newBook = doc.createElement("book");
            Element titleElement = doc.createElement("title");
            titleElement.appendChild(doc.createTextNode(titulo));
            Element authorElement = doc.createElement("author");
            authorElement.appendChild(doc.createTextNode(autor));
            Element dateElement = doc.createElement("publish_date");
            dateElement.appendChild(doc.createTextNode(fecha));
            newBook.appendChild(titleElement);
            newBook.appendChild(authorElement);
            newBook.appendChild(dateElement);

            // Agregar el nuevo libro al documento
            Element root = doc.getDocumentElement();
            root.appendChild(newBook);

            // Eliminar un libro (por ejemplo, el primero)
            NodeList bookList = root.getElementsByTagName("book");
            if (bookList.getLength() > 0) {
                Node firstBook = bookList.item(0);
                root.removeChild(firstBook);
            }

            // Guardar el documento modificado en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("modified_books.xml"));
            transformer.transform(source, result);

            System.out.println("Operaciones de modificación completadas.");
        } catch (Exception e) {
            e.printStackTrace();
        }  
        
    }
}
