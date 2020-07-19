package leadsquared

class Lead {

    String name
    String prospectID
    Date createdAt = new Date()
    Date updatedAt = new Date()

    static constraints = {
        name name: true
        prospectID prospectID: true
        createdAt createdAt:true
        updatedAt updatedAt: true
    }

}
