<#macro login path>
    <form action="${path}" method="post">
        <#nested >
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name : </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User Name" /> </label>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password : </label>
            <div class="col-sm-6">
                <input type="text" name="password"  class="form-control" placeholder="Password"/> </label>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div> <button type="submit" class="btn btn-primary">Submit</button></div>
    </form>
</#macro>

<#macro loguot>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Logout</button>
    </form>
</#macro>