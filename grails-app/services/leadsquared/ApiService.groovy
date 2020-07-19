package leadsquared

import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional
import grails.config.Config
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder
import io.micronaut.core.type.Argument






@Transactional
class ApiService implements GrailsConfigurationAware {

    String appid
    String cityName
    String countryCode
    BlockingHttpClient client


    @Override
    void setConfiguration(Config co) {
        setupHttpClient(co.getProperty('LeadSquared.url', String, 'https://api-in21.leadsquared.com/v2/LeadManagement.svc/'))
    }

    void setupHttpClient(String url) {
        this.client = HttpClient.create(url.toURL()).toBlocking()
    }

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
        }
        catch (Exception e)
        {
            return  'error'
        }
    }

    def updateLeadAPI(payload, leadID) {


        try{
            HttpResponse<Map> resp = client.exchange(HttpRequest.POST(generateURIforUpdate(leadID), [
                    [Attribute: 'EmailAddress', Value: payload[0]],
                    [Attribute: 'FirstName', Value: payload[1]],
                    [Attribute: 'LastName', Value: payload[2]],
                    [Attribute: 'Phone', Value: payload[3]],
                    [Attribute: 'Website', Value: payload[4]],
                    [Attribute: 'LeadSource', Value: payload[5]],
                    [Attribute: 'Notes', Value: payload[6]]
            ]), Map)

            return  resp.body().Message
        }
        catch (Exception e)
        {
            return  'error'
        }
    }

    def getLeadsbyId (id){

        try{
            HttpResponse<Map> resp = client.exchange(HttpRequest.POST(generateURIForGetById(),
                    [SearchParameters: [LeadIds: id],
                     Columns: [Include_CSV: "CreatedOn, ModifiedOn, CompanyType, EmailAddress, ProspectId, OwnerIdEmailAddress, ProspectAutoID, FirstName, LastName, ProspectStage"]
                    ]), Map)
            return resp.body().Leads
        }
        catch (Exception e){
            return 'error'
        }
    }

    def getLeadbByMail(email){


        try {
            HttpResponse<Object> resp = client.exchange(HttpRequest.GET(generateURIforEmail(email)), Object)
            return resp.body()
        }
        catch (Exception e){
           return 'error'
        }

    }

    def deleteLead(leadID){
        println("leadID : " + leadID)

        try {
//            HttpResponse resp = client.exchange(HttpRequest.GET(generateURIforDelete(leadID)))
            println(HttpRequest.GET(generateURIforDelete(leadID)))
//            println resp.body()
//            println("Resp Status : " + resp.status())
            return "OK"
        }
        catch (Exception e){
            println("Exception!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println e
            return 'error'
        }

    }

    def generateURI() {
        UriBuilder uriBuilder = UriBuilder.of('Lead.Create')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
        uriBuilder.build()
    }

    def generateURIforUpdate(leadID) {
        UriBuilder uriBuilder = UriBuilder.of('Lead.Update')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
                .queryParam('leadId', leadID)
        uriBuilder.build()
    }

    def generateURIForGetById(url) {
        UriBuilder uriBuilder = UriBuilder.of('Leads/Retrieve/ByIds')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
        uriBuilder.build()
    }

    def generateURIforEmail(email) {
        UriBuilder uriBuilder = UriBuilder.of('Leads.GetByEmailaddress')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
                .queryParam('EmailAddress', email)
        uriBuilder.build()
    }

    def generateURIforDelete(leadID) {
        UriBuilder uriBuilder = UriBuilder.of('Lead/Delete/ById')
                .queryParam('accessKey', "u\$r9123faa7e5402122cb7375a1a8a5d81b")
                .queryParam('secretKey', "77b7b61f4d94a677b4cdb4b0699c289cab26ac00")
                .queryParam('LeadId', leadID)
        uriBuilder.build()
    }
}
