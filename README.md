# openForBeta
autotests

To run autotests you should perform following steps:

$ git clone https://github.com/ViTaLESs/openForBeta.git

$ mvn clean

$ mvn compile

$ mvn test -p (specify profile)

Test1: - enter to the system; - check main page; - switch to sing up page; - test sign up without fields fill

Test2: - fill Username field - name is already taken - pass length not equals conf pass - incorrect email - check CAPTCHA

Test3: - refresh page - check that fields are empty - click signUp link - check correct switch to MainPage

Test4: - switch to login page; - check correct switch to loginPage; - test login without fields fill; - test rememberMeCheckBox; - test Close Button;

Test5: - test forgot on login form; - test singUpLink on login form; - test incorrect username in RESET your Password; - test correct username in RESET your Password;

Test6: - Correct login to the system; - Correct logOut fo the system;
