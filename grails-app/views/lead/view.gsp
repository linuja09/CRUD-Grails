
<!DOCTYPE html>
    <html>
    <head>
    <meta name="layout"content="main"/>
    <title>View Lead</title>
    </head>
    <body>
    <div class="jumbotron">
            <h1 class="display-4">View Lead</h1>
            <hr class="my-4">
            </div>


     <div class="alert alert-primary" role="alert">
        <div><b>Full Name</b></div>
        <div>${fData?.FirstName[0]} ${fData?.LastName[0]}</div>
       </div>
     <div class="alert alert-primary" role="alert">
        <div><b>Email</b></div>
        <div>${fData?.EmailAddress[0]}</div>
       </div>
     <div class="alert alert-primary" role="alert">
        <div><b>Phone Number</b></div>
        <div>${fData?.Phone[0]}</div>
       </div>
     <div class="alert alert-primary" role="alert">
        <div><b>Website</b></div>
        <div>${fData?.Website[0]}</div>
       </div>
     <div class="alert alert-primary" role="alert">
        <div><b>Notes</b></div>
        <div>${fData?.Notes[0]}</div>
       </div>

     <g:link action="delete" class="btn btn-danger" params="${[prospectID: id]}">Delete</g:link>

    </body>
    </html>

