package ru.nsu.fit.tests.steps;

import ru.nsu.fit.tests.services.browser.MainPageService;

import java.util.HashMap;
import java.util.Map;

public class MyContext {

    static Map<String,Object> variables = new HashMap<String, Object>();

    static public void type_exp(String expr){
        for (int i = 0; i < expr.length(); i++) {
            char symbol = expr.charAt(i);
            switch (symbol) {
                case '1':
                    MainPageService.browser.click(MainPageService.oneElement);
                    break;
                case '2':
                    MainPageService.browser.click(MainPageService.twoElement);
                    break;
                case '3':
                    MainPageService.browser.click(MainPageService.threeElement);
                    break;
                case '4':
                    MainPageService.browser.click(MainPageService.fourElement);
                    break;
                case '5':
                    MainPageService.browser.click(MainPageService.fiveElement);
                    break;
                case '6':
                    MainPageService.browser.click(MainPageService.sixElement);
                    break;
                case '7':
                    MainPageService.browser.click(MainPageService.sevenElement);
                    break;
                case '8':
                    MainPageService.browser.click(MainPageService.eightElement);
                    break;
                case '9':
                    MainPageService.browser.click(MainPageService.nineElement);
                    break;
                case '0':
                    MainPageService.browser.click(MainPageService.zeroElement);
                    break;
                case '+':
                    MainPageService.browser.click(MainPageService.plusElement);
                    break;
                case '*':
                    MainPageService.browser.click(MainPageService.timesElement);
                    break;
                case '-':
                    MainPageService.browser.click(MainPageService.minusElement);
                    break;
                case '/':
                    MainPageService.browser.click(MainPageService.divElement);
                    break;
                default:
                    throw new IllegalArgumentException("Could not find ui element for following symbol: " + symbol);
            }
        }
    }

}
