<#import "parts/common.ftl" as c>
<@c.page>
    <#if messages??>
        <#list messages as message>
            <div class="container my-3">
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <div class="card-title">#${message.tag}</div>
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
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <#else> No news
    </#if>

</@c.page>