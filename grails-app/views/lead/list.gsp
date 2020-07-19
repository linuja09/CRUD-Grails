
<!DOCTYPE html>
    <html>
    <head>
    <meta name="layout"content="main"/>
    <title>List of Leads</title>
    </head>
    <body>
    <h1 class="display-4">List of Leads</h1>



    <table class="table">
        <thead>
        <tr>
        <th scope="col">#</th>
        <th scope="col">Lead Name</th>
        <th scope="col">Email Address</th>
        <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <g:each status="i" in="${leads}" var="data">
        <tr>
        <th scope="row"> ${i+1} </th>
        <td> ${data?.FirstName} ${data?.LastName} </td>
        <td> ${data?.EmailAddress} </td>
        <td>
            <g:link action="update" params="${[email: data?.EmailAddress]}">Update</g:link>
            <g:link action="view" params="${[email: data?.EmailAddress]}">View</g:link>
        </td>
        </tr>
        </g:each>

        </tbody>
        </table>

    </body>
    </html>

