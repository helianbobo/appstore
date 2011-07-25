<%@ page import="appstore.Webapp" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'webapp.label', default: 'Webapp')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
  </head>

  <body>
    <a href="#list-webapp" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

    <div class="nav" role="navigation">
      <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
      </ul>
    </div>

    <div id="list-webapp" class="content scaffold-list" role="main">
      <h1><g:message code="default.list.label" args="[entityName]"/></h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
        <tr>

          <g:sortableColumn property="name" title="${message(code: 'webapp.name.label', default: 'Name')}"/>

          <g:sortableColumn property="imageUrl"
                            title="${message(code: 'webapp.imageUrl.label', default: 'Image Url')}"/>

          <g:sortableColumn property="description"
                            title="${message(code: 'webapp.description.label', default: 'Description')}"/>



          <g:sortableColumn property="packageUrl"
                            title="${message(code: 'webapp.packageUrl.label', default: 'Package Url')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${webappInstanceList}" status="i" var="webappInstance">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="show"
                        id="${webappInstance.id}">${fieldValue(bean: webappInstance, field: "name")}</g:link></td>

            <td><img src="${createLink(uri: webappInstance.imageUrl)}">
              </td>

            <td><g:link action="show"
                        id="${webappInstance.id}">${fieldValue(bean: webappInstance, field: "description")}</g:link></td>



            <td>
              <g:link uri="${webappInstance.packageUrl}">Download</g:link>
            </td>

          </tr>
        </g:each>
        </tbody>
      </table>

      <div class="pagination">
        <g:paginate total="${webappInstanceTotal}"/>
      </div>
    </div>
  </body>
</html>
