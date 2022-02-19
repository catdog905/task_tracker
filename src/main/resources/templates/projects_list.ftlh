<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="ru">
<head>
    <title>List of projects</title>
</head>

<body>
    <div class="container">

        <h1>List of projects:</h1>
        <div class="btn-group">
            <#list projects as project>
            <form method="get" action="/projects_list/${project.id}">
                    <button type="submit">
                        ${project.name}
                    </button>
            </form>
            <form method="get" action="/projects_list/delete_project/${project.id}">
                <button type="submit">
                    Del
                </button>
            </form>
            </#list>
        </div>
        <form method="get" action="/projects_list/create_project">
            <button type="submit">
                Create new project
            </button>
        </form>
    </div>
</body>
</html>