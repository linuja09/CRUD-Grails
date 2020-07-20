package leadsquared

/**
 * Class : LeadController
 * Controller class holds the logic to process the request from the views and render the response to the views
 */
class LeadController {

    def apiService;

    /**
     * Redirect the home page when the application loads
     * @return
     */
    def index() {
        redirect action: "home"
    }

    /**
     * Logic to list the leads in the list page
     */
    def list = {
        // Create criteria to retrieve only prospectID column from Lead table
        def c = Lead.createCriteria()

        // requiredList : holds the list of prospectIDs of Leads stored in the local database
        def requiredList = c.list  {
            projections {
                property("prospectID")
            }
        }

        def data = null

        if (requiredList.size() > 0) {
            data = apiService.getLeadsById (requiredList)
            [leads: data]
        }
    }

    /**
     * Logic to create a Lead record
     */
    def processCreate = {
        // Getting the form data and setting it to an array
        def payload = [params.email, params.firstName, params.lastName, params.phoneNumber, params.website, params.leadSource, params.notes]
        def res = apiService.createLeadAPI(payload)

        if(res != 'error'){
            def id = res.Id

            // There is no api to get all Leads. Therefore storing the Lead name and prospect id in the local database
            // to get the list of leads by passing the prospect ids as search parameter in the api request.
            def data = new Lead()
            data.name = params.firstName
            data.prospectID = id
            data.save()

            // Redirect to list action
            redirect(action:"list")
        } else {
            redirect(action:"create", params: [
                    error: "Something went wrong. Please Try Again",
            ])
        }
    }

    def home = {}

    def create = {
        def error = params.error
        [error: error]
    }

    /**
     * Logic to delete a Lead record
     */
    def delete = {
        def prospectID = params.prospectID
        def res = apiService.deleteLead(prospectID)

        if(res != 'error'){
            // Deleting record from local database
            Lead.where { prospectID == prospectID }.deleteAll()

            // Get all leads stored in local database
            def results = Lead.list()

            // Redirect to list action
            redirect(action:"list", params: [
                    leads: results
            ])
        }
        else {
            redirect(action:"list", params: [
                    error: "Something went wrong. Please Try Again",
            ])
        }
    }

    /**
     * Logic to load the edit page
     */
    def update = {
        def error = params.error
        def email = params.email
        def res = apiService.getLeadByMail(email)
        [fData: res, error: error]
    }

    /**
     * Logic to update a Lead record
     */
    def ProcessUpdate = {
        // Getting the form data and setting it to an array
        def payload = [params.email,params.firstName,params.lastName,params.phoneNumber,params.website,params.leadSource,params.notes]
        def res = apiService.updateLeadAPI(payload, params.prospectID)

        if(res != 'error'){
            // Redirect to list action
            redirect(action:"list")
        } else {
            redirect(action:"update", params: [
                    error: "Something went wrong. Please Try Again",
            ])
        }
    }

    /**
     * Logic to load view page
     */
    def view = {
        def email = params.email
        def res = apiService.getLeadByMail(email)
        [fData: res, id: res.ProspectID[0]]
    }



}
