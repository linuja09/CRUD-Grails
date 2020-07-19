package leadsquared

import org.springframework.web.util.UriBuilder

class LeadController {

    def apiService;

    def index() {
        redirect action: "home"
    }

    def list = {
        def c = Lead.createCriteria()
        def requiredList = c.list  {
            projections {
                property("prospectID")
            }
        }

        def data = null

        if (requiredList.size() > 0) {
            data = apiService.getLeadsbyId (requiredList)
            [leads: data]
        }


    }

    def processCreate = {
        def payload = [params.email,params.firstName,params.lastName,params.phoneNumber,params.website,params.leadSource,params.notes]
        def res = apiService.createLeadAPI(payload)

        if(res != 'error'){
            def id = res.Id

            def data = new Lead()
            data.name = params.firstName
            data.prospectID = id

            data.save()

            def results = Lead.list()

            redirect(action:"list", params: [
                    leads: results
            ])

        }
        else {
            redirect(action:"create", params: [
                    error: "Something went wrong. Please Try Again",
            ])
        }
    }

    def home = {}

    def create = {
        def error =  null
        [error: error]
    }

    def delete = {
        def prospectID = params.prospectID
        def res = apiService.deleteLead(prospectID)

        println(prospectID)

        println("Deleted 1")

        if(res != 'error'){
            Lead.where { prospectID == prospectID }.deleteAll()

            println("Deleted 2")

            def results = Lead.list()
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

    def update = {
        def email = params.email
        def res = apiService.getLeadbByMail(email)
        [fData: res]
    }

    def ProcessUpdate = {
        def payload = [params.email,params.firstName,params.lastName,params.phoneNumber,params.website,params.leadSource,params.notes]
        def res = apiService.updateLeadAPI(payload, params.prospectID)

        if(res != 'error'){
            def results = Lead.list()

            redirect(action:"list", params: [
                    leads: results
            ])
        }
        else {
            redirect(action:"update", params: [
                    error: "Something went wrong. Please Try Again",
            ])
        }
    }

    def view = {
        def email = params.email
        def res = apiService.getLeadbByMail(email)
        [fData: res, id: res.ProspectID[0]]
    }



}
