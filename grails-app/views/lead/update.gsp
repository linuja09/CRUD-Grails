
<!DOCTYPE html>
    <html>
    <head>
    <meta name="layout"content="main"/>
    <title>Update Lead</title>
    </head>
    <body>
    <div class="jumbotron">
            <h1 class="display-4">Edit Lead ${fData?.FirstName[0]} </h1>
            <hr class="my-4">

            <g:form name="updateForm" action="processUpdate">
            <g:field type="hidden" name="prospectID" value="${fData?.ProspectID[0]}" class="form-control"/>
            <div class="form-row">
                <div class="form-group col-md-6">
                  <label for="firstName">First Name</label>
                  <g:field type="text" name="firstName" value="${fData?.FirstName[0]}" class="form-control"/>

                </div>
                <div class="form-group col-md-6">
                  <label for="lastName">Last Name</label>
                  <g:field type="text" name="lastName" value="${fData?.LastName[0]}" class="form-control"/>
                </div>
            </div>

            <div class="form-row">
                        <div class="form-group col-md-6">
                          <label for="email">Email</label>
                          <g:field type="email" name="email" value="${fData?.EmailAddress[0]}" class="form-control"/>
                        </div>
                        <div class="form-group col-md-6">
                          <label for="phoneNumber">Phone Number</label>
                          <g:field type="text" name="phoneNumber" value="${fData?.Phone[0]}" class="form-control"/>
                        </div>
                    </div>

            <div class="form-row">
                        <div class="form-group col-md-6">
                          <label for="website">Website</label>
                          <g:field type="text" name="website" value="${fData?.Website[0]}" class="form-control"/>
                        </div>

                    </div>

            <div class="form-group">
                <label for="notes">Notes</label>

                <g:field type="text" name="notes" value="${fData?.Notes[0]}" class="form-control"/>
              </div>
              <button type="submit" class="btn btn-primary">Update</button>
            </g:form>

         </div>

    </body>
    </html>

