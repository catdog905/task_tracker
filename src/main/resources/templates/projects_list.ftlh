<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="ru">
<head>
    <title>List of projects</title>
    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h1>List of projects:</h1>
        </div>
        <div class="card-body">
            <div class="btn-group">
                <table>
                    <tr>
                        <td><b>Project name:<b/></td>
                    </tr>
                    <#list projects as project>
                        <tr>
                            <td>
                                <form method="get" action="/projects_list/${project.id}">
                                    <button type="submit">
                                        ${project.name}
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form method="get" action="/projects_list/delete_project/${project.id}">
                                    <button type="submit">
                                        Del
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </#list>
                </table>
            </div>
        </div>
    </div>
    <form method="get" action="/projects_list/create_project">
        <button type="submit" type="button" class="btn btn-secondary btn-lg btn-block">
            Create new project
        </button>
    </form>
</div>
</body>
</html>