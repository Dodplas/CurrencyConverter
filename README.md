Build jar with `mvn clean install package`

run with `java -jar target/currency-converter-1.0.jar 1.11 AED BAM`
The parameters in the command are:
- A source currency amount that you want to convert (in the format x.yy)
- A source ISO 4217 currency code 
- A target ISO 4217 currency code


If more functionality was wanted then you could look into the https://github.com/remkop/picocli library. 

This would handle multiple commands in a nicer manner however I considered it unnecessary for this one command application.
