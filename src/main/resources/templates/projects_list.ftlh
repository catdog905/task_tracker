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
                        <td><b>Project name:</b></td>
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
                <table><tbody>
                        <form method="post" action="/projects_list/sort">
                    <tr>
                        <td>Sort by:</td>
                        <td>
                            <select name="projectSortBy" id="tasksSortBy">
                                <option value="NAME"> Name</option>
                                <option value="START_DATE">Start date</option>
                                <option value="COMPLETION_DATE">Completion date</option>
                                <option value="STATUS">Status</option>
                                <option value="PRIORITY">Priority</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>How sort:</td>
                        <td>
                            <select id="howSort" name="howSort">
                                <option value="ASCENDING">
                                    Ascending
                                </option>
                                <option value="DESCENDING">
                                    Descending
                                </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <button type="submit">
                            Sort
                        </button>
                    </tr>
                    </form>
                    </tr>
                    </tbody></table>
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