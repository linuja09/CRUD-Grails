package leadsquared

class BootStrap {

    def init = { servletContext ->
        new Lead(name:"Deenath", prospectID:"3e18a540-e9ca-42de-92b2-afdfd8aa954e").save()
        new Lead(name:"Linu", prospectID:"8fbb2f50-4ff3-431b-9280-736e0a2591da").save()
        new Lead(name:"Col", prospectID:"365bd2ce-ef2d-40fc-ada7-c01cb88c4d1c").save()
    }
    def destroy = {
    }
}
