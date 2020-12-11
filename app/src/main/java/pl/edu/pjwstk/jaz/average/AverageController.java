package pl.edu.pjwstk.jaz.average;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AverageController {

    @GetMapping("average")
    public String getAverage(@RequestParam(value = "numbers", required = false) String numbers){
        //policzyć srednia
        if(numbers==null || numbers.isEmpty()){
            return "nie podano parametrow";
        }
        else{   
            String[] parts = numbers.split(",");
            double suma=0;
            for (String part : parts) {
                int x = Integer.parseInt(part);
                suma += x;
            }
            BigDecimal srednia= new BigDecimal(suma/(parts.length));
            srednia = srednia.setScale(2, BigDecimal.ROUND_HALF_UP);

            srednia=srednia.stripTrailingZeros();

            return srednia.toString();
        }
    }
}
