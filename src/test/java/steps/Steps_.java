package steps;

import base.BaseWeb;
import io.cucumber.java.pt.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Steps_ extends BaseWeb {
        @Dado("^que estou no site informado")
        public void acessar_site_informado () {
            driver.get("https://www.discourse.org/");
        }

        @Quando("^clicar em demo e fazer scroll")
        public void irprarademo() throws InterruptedException {
            String browser = driver.getWindowHandle();
            driver.findElement(By.linkText("Demo")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Wait 5 sec to load the page

            ArrayList<String> newBrowser = new ArrayList<>(driver.getWindowHandles());
            newBrowser.remove(browser);
            driver.switchTo().window(newBrowser.get(0));

            // Scroll is rolling down until find the element at the end of the page
            JavascriptExecutor javaScript = (JavascriptExecutor) driver;
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
            WebElement textEndOfPage = driver.findElement(By.tagName("h3"));
            javaScript.executeScript("arguments[0].scrollIntoView();", textEndOfPage);
        }

        @E("^imprimir os titulos fechados")
        public void imprimirtitulosfechados() {
            System.out.println("O título de todos os tópicos fechados");
            List<WebElement> topicoCadeado = driver.findElements((By.cssSelector("tr.closed")));
            for (int i = 0; i < topicoCadeado.size(); i++) {
                String itenNome = topicoCadeado.get(i).findElement(By.cssSelector("a.title")).getText(); // a #TAG to get the titles
                System.out.println((i + 1) + " = " + itenNome);
            }
        }

        @Entao("^imprimir a quantidade de itens de cada categoria")
        public void imprimirquantidadedeitens() {
            System.out.println("Quantidade de itens de cada categoria");
            List<WebElement> categorias = driver.findElements((By.className("category-name")));
            List<String> lista = new ArrayList<>();
            // adding categories and manipulating inside the loop
            for (WebElement webElement : categorias) {
                lista.add(webElement.getText());
            }

            // creating a hashing MAP to manipulating
            Map<String, Integer> map = new HashMap<>();
            for (String categoriesName : lista) {
                if (map.containsKey(categoriesName)) {
                    map.put(categoriesName, map.get(categoriesName) + 1);
                } else
                    map.put(categoriesName, 1);
            }

            map.forEach((categoria, topico) -> {
                if (topico > 0)
                    System.out.println(categoria + " " + topico);
            });

            List<WebElement> semCategoria = driver.findElements(By.className("category-uncategorized"));

            // "category-uncategorized" is the key to all topics with no categories
            // Criating a list to capture all the "category-uncategorized"

            System.out.println("___________________");
            System.out.println("Itens sem categoria  " + semCategoria.size());
        }


        @Entao("^devo imprimir o titulo com mais views")
        public void imprimirotitulocommaisviews() {
            String Maiorviews = driver.findElement(By.xpath("//tr[26]/td[1]/span/a")).getText();
            System.out.println("_________________");
            System.out.println("Item com maior numero de views:" + " " +Maiorviews);
        }
    }
