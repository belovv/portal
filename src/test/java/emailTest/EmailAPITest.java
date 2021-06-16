package emailTest;


public class EmailAPITest {

 /*   Email email = new EmailProvider().getEmailProvider();
    RetrofitSteps retrofitSteps = new RetrofitSteps();
    UserProvider userProvider = ConfigFactory.create(UserProvider.class);
    EmailMessage emailMessage = ConfigFactory.create(EmailMessage.class);

    @Disabled
    @TestLayer.PersonalCabinet.email.createNewCustomer
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Создаем аккаунт в ЛК, проверяем письма")
    @TestType.email
    public void createCustomerLKByAPI() {

        email.deleteAllMessage();
        String orgName = retrofitSteps.createCustomerLK();
        email.waitAndCheckCountNewEmail(2);
     //   Message userEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectConfirmRegistrationCustomerLK());
    //    String textUserEmail = email.getMessageContent(userEmail);
    //    String registrationToken = email.getUrlsFromMessage(userEmail).get(0);
        //-----------------------------Копия предыдущего письма менеджерам-------------------------------
        Message managersEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectConfirmRegistrationManagersLK());
        String textManagersEmail = email.getMessageContent(managersEmail);
        assertAll("ConfirmRegistration",
            //    () -> assertEquals(1, email.getAddressOfRecipients(userEmail).size(), "Получателей письма больше одного"),
              //  () -> assertTrue(email.findEmailAddress(userEmail, userProvider.newCustomerEmailAPILK()), "Заказчик и получатель письма не совпадают"),
                () -> assertTrue(textUserEmail.contains(emailMessage.textWithRegistrationLinkCustomerPart1())),
                () -> assertTrue(textUserEmail.contains(emailMessage.textWithRegistrationLinkCustomerPart2())),
                //----------------------------------------------------------------------------------------------
           //     () -> assertEquals(3, email.getAddressOfRecipients(managersEmail).size(), "получателей письма не 3"),
            //    () -> assertTrue(email.findEmailAddress(managersEmail, userProvider.emailServiceManager())),
            //    () -> assertTrue(email.findEmailAddress(managersEmail, userProvider.emailService2())),
            //    () -> assertTrue(email.findEmailAddress(managersEmail, userProvider.emailSalesManager())),
                () -> assertTrue(textManagersEmail.contains(emailMessage.startMessageManagers() + "\"" + orgName + "\"")),
                () -> assertTrue(textManagersEmail.contains(emailMessage.textWithRegistrationLinkManagers()))
        );
        email.deleteAllMessage();
        retrofitSteps.confirmEmail(registrationToken);
        email.waitAndCheckCountNewEmail(3);
        Message newCustomerEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectRegistrationDefaultManagerLK() + orgName);
        String textNewCustomerEmail = email.getMessageContent(newCustomerEmail);
        String customerNumber = email.getUrlsFromMessage(newCustomerEmail).get(0);
        String customerCID = (retrofitSteps.getCustomerInfo(customerNumber));
        //---------------------------------------------------------------------------------------
        Message customerNewRegistrationEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectRegistrationCustomerLK());
        String textCustomerNewRegistrationEmail = email.getMessageContent(customerNewRegistrationEmail);
        //---------------------------------------------------------------------------------------
        Message managersNewRegistrationEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectRegistrationManagerLK());
        String textManagerNewRegistrationEmail = email.getMessageContent(managersNewRegistrationEmail);
//
        assertAll("RegistrationEmails",
             //   () -> assertEquals(1, email.getAddressOfRecipients(newCustomerEmail).size(), "Письмо отправлено не одному адресату"),
            //    () -> assertTrue(email.findEmailAddress(newCustomerEmail, userProvider.emailSalesManager())),
                () -> assertTrue(textNewCustomerEmail.contains(emailMessage.textNewCustomerEmailPart1() + "\"" + orgName + "\"" + emailMessage.textNewCustomerEmailPart2())),
                () -> assertTrue(textNewCustomerEmail.contains(emailMessage.textNewCustomerEmailPart3())),
                //---------------------------------------------------------------------
            //    () -> assertEquals(1, email.getAddressOfRecipients(customerNewRegistrationEmail).size(), "Письмо отправлено не одному адресату"),
           //     () -> assertTrue(email.findEmailAddress(customerNewRegistrationEmail, userProvider.newCustomerEmailAPILK())),
                () -> assertEquals(emailMessage.registrationMessageCustomerLK(), textCustomerNewRegistrationEmail),
                //_____________________________________________________________________
          //      () -> assertEquals(3, email.getAddressOfRecipients(managersNewRegistrationEmail).size(), "Получателей письма не 3"),
          //      () -> assertTrue(email.findEmailAddress(managersNewRegistrationEmail, userProvider.emailServiceManager())),
           //     () -> assertTrue(email.findEmailAddress(managersNewRegistrationEmail, userProvider.emailService2())),
          //      () -> assertTrue(email.findEmailAddress(managersNewRegistrationEmail, userProvider.emailSalesManager())),
                () -> assertEquals(emailMessage.startMessageManagers() + "\"" + orgName + "\"" + " [" + customerCID + "]" + emailMessage.textRegistrationManagers(), textManagerNewRegistrationEmail)
        );
    }
    @Disabled
    @TestLayer.PersonalCabinet.email.createNewCustomer
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Создаем аккаунт в Админке, проверяем письма")
    @TestType.email
    public void createCustomerAdminByAPI() {

        email.deleteAllMessage();
        String name = retrofitSteps.createCustomerAdmin();
        email.waitAndCheckCountNewEmail(2);
        Message customerEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectCustomerRegistrationFromAdmin());
      //  List<String> addressCustomer = email.getAddressOfRecipients(customerEmail);
        String textCustomerEmail = email.getMessageContent(customerEmail);
        //----------------------------------------------------------------------
        Message managersEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectManagersRegistrationFromAdmin());
        String textManagersEmail = email.getMessageContent(managersEmail);
        assertAll("RegistrationEmailsAdmin",
          //      () -> assertEquals(1, addressCustomer.size(), "Письмо отправлено не одному адресату"),
          //      () -> assertTrue(email.findEmailAddress(customerEmail, userProvider.newCustomerEmailAPIAdmin())),
                () -> assertTrue(textCustomerEmail.contains(emailMessage.textCustomerRegistrationFromAdminPart1())),
                () -> assertTrue(textCustomerEmail.contains(emailMessage.textCustomerRegistrationFromAdminPart2())),
                //----------------------------------------------------------------------------------------------
         //       () -> assertEquals(3, email.getAddressOfRecipients(managersEmail).size(), "получателей письма не 3"),
         //       () -> assertTrue(email.findEmailAddress(managersEmail, userProvider.emailServiceManager())),
        //        () -> assertTrue(email.findEmailAddress(managersEmail, userProvider.emailService2())),
         //       () -> assertTrue(email.findEmailAddress(managersEmail, userProvider.emailSalesManager())),
                () -> assertTrue(textManagersEmail.contains(emailMessage.startMessageManagers() + name)),
                () -> assertTrue(textManagersEmail.contains(emailMessage.textManagersRegistrationFromAdminPart1())),
                () -> assertTrue(textManagersEmail.contains(emailMessage.textManagersRegistrationFromAdminPart2()))
        );
    }

    @Disabled //TODO доделать когда будет база
    @TestLayer.PersonalCabinet.email.createNewCustomer
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заключаем индивидиальный договор, проверяем письма")
    @TestType.email
    public void signIndividualContractByAPI() {

       emailUtils.deleteAllMessage();
        httpRequests.sendIndividualContract();
        email.waitAndCheckCountNewEmail(2);
        Message customerEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectCustomerIndividualContract());
    //    List<String> addressCustomer = email.getAddressOfRecipients(customerEmail);
        String textCustomerEmail = email.getMessageContent(customerEmail);
        //----------------------------------------------------------------------
        Message managersEmail = email.waitAndGetNewLetterBySubject(emailMessage.subjectManagersRegistrationFromAdmin());
    //    List<String> address = email.getAddressOfRecipients(managersEmail);
        String textManagersEmail = email.getMessageContent(managersEmail);
    }*/
}
