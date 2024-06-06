# Appolica-AssessmentCenter-Task
Task: currency convertor Create a CLI application that integrates with Fast Forex and lets the user convert currencies with exchange rates from past dates.

# Requirements
<br>• Use Python, Node.js or Kotlin.</br>
<br>• The application must accept a command line argument for the date in format '2024-12-31'.</br>
<br>• The application must be able to process multiple conversions.</br>
<br>• The application must continuously validate all inputs until a correct one is submitted. Мonetary values should be constrained to two decimal places. Currencies must be in ISO 4217 three letter currency code format.</br>
<br>• The application must be case-insensitive.</br>
<br>• The application must cache the exchange rates for each requested base currency. Subsequent conversions with this base currency should use the cached data, instead of calling the API.</br>
<br>• Each successful conversion must be saved in a json file with the provided format.</br>
<br>• The application must be terminated by typing 'END' on any input.</br>
<br>• The application must load the api_key for Fast Forex from a config.json file which must be ignored by the version control.</br>
<br>• The executable must be named CurrencyConversion.</br>
<br>For the purposes of this exercise consider that the API calls will never fail.</br>

# Example
<br>The application must accept input for Amount, Base currency and Target currency in the given order.</br>
<br>• Input in the example is marked as such</br>
‍<br>• DO NOT include the labels for the input (e.g. ‘Amount’, ‘Base currency', etc).</br>
<br>• Output in the example is <b>BOLD</b>.</br>

<br>//Starts the app with the command line argument</br>
<br>python3 CurrencyConversion.py 2024-05-13</br>

<br>//Amount</br>
<br>10.123</br>
<br>Please enter a valid amount</br>

<br>//Amount</br>
<br>10.23</br>
<br>//Base currency</br>
<br>bng</br>
<br>Please enter a valid currency code</br>

<br>//Base currency</br>
<br>bgn</br>
<br>//Target currency</br>
<br>ERO</br>
<br>Please enter a valid currency code</br>

<br>//Target currency</br>
<br>EUR</br>

<br>10.23 BGN is 5.23 EUR</br>

<br>//Ends the app</br>
<br>end</br>
<br>Example format for conversions.json file:</br>
<br>[ </br>
<br>    {
<br>        "date": "2024-05-22"</br>
<br>        "amount": 10.23,</br>
<br>        "base_currency": "BGN", </br>
<br>        "target_currency": "EUR", </br>
<br>        "converted_amount": 5.23 </br>
<br>    }</br>
<br>]</br>
