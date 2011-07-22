<%@ page import="appstore.Webapp" %>







<div class="fieldcontain ${hasErrors(bean: webappInstance, field: 'name', 'error')} ">
  <label for="name">
    <g:message code="webapp.name.label" default="Name"/>

  </label>
  <g:textField name="name" value="${webappInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: webappInstance, field: 'description', 'error')} ">
  <label for="description">
    <g:message code="webapp.description.label" default="Description"/>

  </label>
  <g:textField name="description" value="${webappInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: webappInstance, field: 'imageUrl', 'error')} ">
  <label for="imageUrl">
    <g:message code="webapp.imageUrl.label" default="Image Url"/>

  </label>
  <input type="file" name="image"/>
  %{--<g:textField name="imageUrl" value="${webappInstance?.imageUrl}"/>--}%
</div>

<div class="fieldcontain ${hasErrors(bean: webappInstance, field: 'packageUrl', 'error')} ">
  <label for="packageUrl">
    <g:message code="webapp.packageUrl.label" default="Package Url"/>

  </label>
  <input type="file" name="package"/>
  %{--<g:textField name="packageUrl" value="${webappInstance?.packageUrl}"/>--}%
</div>

