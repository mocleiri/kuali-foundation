${"freemarker.test.templatesuite.models.NewTestModel"?new("works")}
<#attempt>
${"freemarker.test.templatesuite.models.NewTestModel2"?new("works")}
<#recover>
fails
</#attempt>

<#include "new-optin-2.ftl">