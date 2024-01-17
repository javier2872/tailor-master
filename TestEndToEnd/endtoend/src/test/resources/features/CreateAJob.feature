@All_Tests
Feature: Create a job and check the job creation
  I want, as a client, to order a new job
  I want, as a tailor, to check this order

  @test_job
  Scenario: Create and check job
    Given I create a test tailor 
    And I navigate to "http://localhost:3000/"
    When I push on "Cliente" in the "login_page"
    And I select the created tailor of test
    And I select two specialities
    And I select one availability
    And I push on "Contratar Servicios" in the "client_details_page"
    And I see the summary of the order
    And I fill the field "Nombre del cliente"
    And I push on "Finalizar" in the "client_summary_page"
    And I see the pop-up
    And I push on "Aceptar" in the "popup_client_summary_page"
    And I push on "Sastre" in the "login_page"
    And I fill the field "ID de Sastre"
    And I push on "Entrar" in the "login_page"
    Then I check the tailor dont have availability
    And I check an order in the order's table