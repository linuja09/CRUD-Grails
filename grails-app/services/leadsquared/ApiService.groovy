package leadsquared

import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional
import grails.config.Config
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.uri.UriBuilder

/**
 * Class : ApiService
 * Service class which holds methods to send requests to the CRM and handle responsed from the CRM
 */
@Transactional
class ApiService implements GrailsConfigurationAware {

    BlockingHttpClient client

    @Override
    void setConfiguration(Config co) {
        setupHttpClient(co.getProperty('LeadSquared.url', String,
                'https://api-in21.leadsquared.com/v2/LeadManagement.svc/'))
    }

    void setupHttpClient(String url) {
        this.client = HttpClient.create(url.toURL()).toBlocking()
    }

    /**
     * Sends request to the CRM to create a LEAD record
     * @param payload
     * @return
     */
    def createLeadAPI(payload) {
        try{
            HttpResponse<Map> resp = client.exchange(HttpRequest.POST(generateURI(), [
                     [Attribute: 'EmailAddress', Value: payload[0]],
                     [Attribute: 'FirstName', Value: payload[1]],
                     [Attribute: 'LastName', Value: payload[2]],
                     [Attribute: 'Phone', Value: payload[3]],
                     [Attribute: 'Website', Value: payload[4]],
                     [Attribute: 'LeadSource', Value: payload[5]],
                     [Attribute: 'Notes', Value: payload[6]]
            ]), Map)

            return  resp.body().Message
        } catch (Exception e) {
            return  'error'
        }
    }

    /**
     * Sends request to the CRM to update a LEAD record
     * @param payload
     * @param leadID
     * @return
     */
    def updateLeadAPI(payload, leadID) {
        try{
            HttpResponse<Map> resp = client.exchange(HttpRequest.POST(generateURIForUpdate(leadID), [
                    [Attribute: 'EmailAddress', Value: payload[0]],
                    [Attribute: 'FirstName', Value: payload[1]],
                    [Attribute: 'LastName', Value: payload[2]],
                    [Attribute: 'Phone', Value: payload[3]],
                    [Attribute: 'Website', Value: payload[4]],
                    [Attribute: 'LeadSource', Value: payload[5]],
                    [Attribute: 'Notes', Value: payload[6]]
            ]), Map)

            return  resp.body().Message
        } catch (Exception e) {
            return  'error'
        }
    }

    /**
     * Sends request to the CRM to get all leads by prospect Ids by passing an array of Prospect Ids as search parameter
     * @param id
     * @return
     */
    def getLeadsById (id){

        try{
            HttpResponse<Map> resp = client.exchange(HttpRequest.POST(generateURIForGetById(),
                    [SearchParameters: [LeadIds: id],
                     Columns: [Include_CSV: "CreatedOn, ModifiedOn, CompanyType, EmailAddress, ProspectId, " +
                             "OwnerIdEmailAddress, ProspectAutoID, FirstName, LastName, ProspectStage"]
                    ]), Map)
            return resp.body().Leads
        }
        catch (Exception e){
            return 'error'
        }
    }

    /**
     * Sends requset to CRM to get a lead by email
     * @param email
     * @return
     */
    def getLeadByMail(email){
        try {
            HttpResponse<Object> resp = client.exchange(HttpRequest.GET(generateURIForEmail(email)), Object)
            return resp.body()
        }
        catch (Exception e){
           return 'error'
        }
    }

    /**
     * Sends a request to CRM to delete a lead by prospect Id
     * @param leadID
     * @return
     */
    def deleteLead(leadID){
        try {
            HttpResponse resp = client.exchange(HttpRequest.GET(generateURIForDelete(leadID)))
            return "OK"
        }
        catch (Exception e){
            return 'error'
        }
    }

    /**
     * Generate URI for Create
     * @return
     */
    def generateURI() {
        UriBuilder uriBuilder = UriBuilder.of('Lead.Create')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
        uriBuilder.build()
    }

    /**
     * Generate URI for Update
     * @param leadID
     * @return
     */
    def generateURIForUpdate(leadID) {
        UriBuilder uriBuilder = UriBuilder.of('Lead.Update')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
                .queryParam('leadId', leadID)
        uriBuilder.build()
    }

    /**
     * Generate URI for get lead by ID
     * @param url
     * @return
     */
    def generateURIForGetById(url) {
        UriBuilder uriBuilder = UriBuilder.of('Leads/Retrieve/ByIds')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
        uriBuilder.build()
    }

    /**
     * Generate URI for get lead by email
     * @param email
     * @return
     */
    def generateURIForEmail(email) {
        UriBuilder uriBuilder = UriBuilder.of('Leads.GetByEmailaddress')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
                .queryParam('EmailAddress', email)
        uriBuilder.build()
    }

    /**
     * Generate URI for delete
     * @param leadID
     * @return
     */
    def generateURIForDelete(leadID) {
        UriBuilder uriBuilder = UriBuilder.of('Lead/Delete/ById')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
                .queryParam('LeadId', leadID)
        uriBuilder.build()
    }
}
