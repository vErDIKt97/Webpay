<#import "parts/common.ftl" as c>
<@c.page>
    <div>
        Search by tag
    </div>
    <div>
        <form class="form-group mt-3" action="/news" method="post">
            <label>
                <input class="form-control" list="tag" name="tag">
            </label>
            <datalist id="tag">
                <#list tags as tag>
                <option value="${tag}">
                    </#list>
            </datalist>
            <label>
                <input class="form-control" type="date" id="date" name="date"
                       value="${time}">
            </label>
            <button class="btn btn-primary" type="submit">Filter</button>
            <input type="hidden" value="${_csrf.token}" name="_csrf" />
        </form>
    </div>
    <#if messages??>
        <#list messages as message>
            <div class="container my-3">
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <div class="card-title">#${message.tag}  Author:${message.author.name} ${message.author.surname}</div>
                                <div class="card-text">
                                    <div class="my-3" data-id="${message.id}">
                                        <#if message.filename??>
                                            <img src="/img/${message.filename}" class="card-img-top" />
                                        </#if>
                                        <div class="m-2">
                                            <span>${message.text}</span><br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-muted">
                                ${message.date}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    <#else> No news
    </#if>

</@c.page>