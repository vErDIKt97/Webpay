<#macro login path>
    <form action="${path}" method="post">
        <#nested >
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-6">
                <input type="text" name="username"
                       class="form-control ${(usernameError??)?string('is-invalid','')}"
                       placeholder="User Name"
                       value="<#if user??>${user.username}</#if>"/> </label>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input type="text" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid','')}"
                       placeholder="Password"/> </label>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
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