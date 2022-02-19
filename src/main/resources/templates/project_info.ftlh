<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="ru">
<head>
    <title>Project information</title>
    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
</head>

<body>
<div class="container">


    <div class="card">
        <form method="get" action="/">
            <button type="submit" type="button" class="btn btn-primary btn-lg">
                <--- BACK
            </button>
        </form>

            <div class="card-header">
                <h1>Project: ${project.name}</h1>
            </div>
            <div class="card-body">
                <table>
                    <tbody>
                    <tr>
                        <form method="post" action="/projects_list/${project.id}/submit_data">
                        <td>
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <b>Type new project name: </b>
                                    </td>
                                    <td>
                                        <input id="name" name="name"  value="${project.name}" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center; font-weight:bold;">Status</td>
                                    <td style="text-align: center;">${project.status}</td>
                                    <td style="text-align: center;">
                                        <select id="status" name="status" >
                                            <option <#if project.status.toString()=='NOT_STARTED'>selected="selected"</#if>
                                                    value="NOT_STARTED"> Not started
                                            </option>
                                            <option <#if project.status.toString()=='ACTIVE'>selected="selected"</#if>
                                                    value="ACTIVE"> Active
                                            </option>
                                            <option <#if project.status.toString()=='COMPLETE'>selected="selected"</#if>
                                                    value="COMPLETE"> Complete
                                            </option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center; font-weight:bold;">Priority</td>
                                    <td style="text-align: center;">${project.priority}</td>
                                    <td style="text-align: center;">
                                        <input id="priority" name="priority" type="number" value="${project.priority}" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center; font-weight:bold;">Project start date</td>
                                    <td style="text-align: center;">${project.startDate}</td>
                                    <td style="text-align: center;">
                                        <input id="startDate" name="startDate" type="datetime-local" value="${project.startDate}" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center; font-weight:bold;">Project completion date</td>
                                    <td style="text-align: center;">${project.completionDate}</td>
                                    <td style="text-align: center;">
                                        <input id="completionDate" name="completionDate" type="datetime-local" value="${project.completionDate}" required>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                        <button type="submit" class="btn btn-primary btn-lg btn-block">
                            Apply changes
                        </button>
        </form>
        <td>
            <table >
                                <tbody>
                                <tr>
                                    <td><b>Tasks:</b></td>
                                </tr>
                                <#list project.taskSet as task>
                                    <tr>
                                        <td>
                                            <form method="get" action="/task_list/${task.id}">
                                                <button type="submit">
                                                    ${task.name}
                                                </button>
                                            </form>
                                        </td>
                                        <td>
                                            <form method="get" action="/projects_list/${project.id}/delete_task/${task.id}">
                                                <button type="submit">
                                                    Del
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
        </td>
        </tr>
        </tbody>
        </table>
    </div>



        <form method="get" action="/projects_list/${project.id}/create_task">
            <button type="submit" class="btn btn-secondary btn-lg btn-block">
                Create new task
            </button>
        </form>
    </div>
</div>
</body>
</html>