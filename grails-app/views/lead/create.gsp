
<!DOCTYPE html>
    <html>
    <head>
    <meta name="layout"content="main"/>
    <title>Create Lead</title>
    </head>
    <body>

    <div class="jumbotron">
        <h1 class="display-4">Create Lead</h1>
        <hr class="my-4">

        <g:if test="${error != null}">
          <div class="alert alert-danger" role="alert">
                 ${error}!
           </div>
        </g:if>



        <g:form name="createForm"  url="[action: 'processCreate']">
        <div class="form-row">
            <div class="form-group col-md-6">
              <label for="firstName">First Name</label>
              <input type="text" class="form-control" id="firstName" name="firstName">
            </div>
            <div class="form-group col-md-6">
              <label for="lastName">Last Name</label>
              <input type="text" class="form-control" id="lastName" name="lastName">
            </div>
        </div>

        <div class="form-row">
                    <div class="form-group col-md-6">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" name="email">
                    </div>
                    <div class="form-group col-md-6">
                      <label for="phoneNumber">Phone Number</label>
                      <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
                    </div>
                </div>

        <div class="form-row">
                    <div class="form-group col-md-6">
                      <label for="website">Website</label>
                      <input type="text" class="form-control" id="website" name="website">
                    </div>
                    <div class="form-group col-md-6">
                      <label for="leadSource">Lead Source</label>
                        <div>
                       <select class="form-control" name="leadSource">
                                  <option>Organic Search</option>
                                                   <option>Referral Sites</option>
                                                   <option>Direct Traffic</option>
                                                   <option>Social Media</option>
                                                   <option>Inbound Email</option>
                                                   <option>Inbound Phone call</option>
                                                   <option>Outbound Phone call</option>
                                                   <option>Pay per Click Ads</option>
                                                   <option>Trade Show</option>
                                                   <option>Customer Referral</option>
                                                   <option>Partner Referral</option>
                                                   <option>Employee Referral</option>
                                                   <option>Others</option>
                        </select>
                        </div>

                    </div>
                </div>

        <div class="form-group">
            <label for="notes">Notes</label>
            <textarea class="form-control" id="notes" rows="3" name="notes"></textarea>
          </div>
          <button type="submit" class="btn btn-primary">Create</button>
        </g:form>

     </div>

    </body>
    </html>

