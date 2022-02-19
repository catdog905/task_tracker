<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="ru">
<head>
    <title>Project information</title>
</head>

<body>
    <form method="get" action="/">
        <button type="submit">
            BACK
        </button>
    </form>
    <form method="post" action="/projects_list/${project.id}/submit_data">
        <h1>Project: ${project.name}</h1>
        <input id="name" name="name" value="${project.name}" required>
        <table>
            <tbody>
            <tr>
                <td style="text-align: center;">Status</td>
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
                <td style="text-align: center;">Priority</td>
                <td style="text-align: center;">${project.priority}</td>
                <td style="text-align: center;">
                    <input id="priority" name="priority" type="number" value="${project.priority}" required>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;">Project start date</td>
                <td style="text-align: center;">${project.startDate}</td>
                <td style="text-align: center;">
                    <input id="startDate" name="startDate" type="datetime-local" value="${project.startDate}" required>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;">Project completion date</td>
                <td style="text-align: center;">${project.completionDate}</td>
                <td style="text-align: center;">
                    <input id="completionDate" name="completionDate" type="datetime-local" value="${project.completionDate}" required>
                </td>
            </tr>
            </tbody>
        </table>
        <button type="submit">
            Apply changes
        </button>
    </form>
    <div>
        <p>Tasks:</p>
        <div class="btn-group">
            <#list project.taskSet as task>
                <form method="get" action="/task_list/${task.id}">
                    <button type="submit">
                       ${task.name}
                    </button>
                </form>
                <form method="get" action="/projects_list/${project.id}/delete_task/${task.id}">
                    <button type="submit">
                        Del
                    </button>
                </form>
            </#list>
        </div>
        <form method="get" action="/projects_list/${project.id}/create_task">
            <button type="submit">
                Create new task
            </button>
        </form>
    </div>
</body>
</html>