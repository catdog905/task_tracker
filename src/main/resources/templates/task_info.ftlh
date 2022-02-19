<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="ru">
<head>
    <title>Task information</title>
</head>

<body>
    <form method="get" action="/projects_list/${task.project.id}">
        <button type="submit">
            BACK
        </button>
    </form>
    <form method="post" action="/task_list/${task.id}/submit_data">
        <h1>Task: ${task.name} </h1>
        <input id="name" name="name" value="${task.name}" required>
        <table style="border-collapse: collapse; width: 100%;">
            <tbody>
            <tr>
                <td style="width: 100%;">
                    <h3>Description:</h3>
                    <textarea id="description" name="description">${task.description}</textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr>
                <td style="text-align: center;">Status</td>
                <td style="text-align: center;">${task.status}</td>
                <td style="text-align: center;">
                    <select id="status" name="status">
                        <option <#if task.status.toString()=='TODO'>selected="selected"</#if>
                                value="TODO"> ToDo
                        </option>
                        <option <#if task.status.toString()=='IN_PROGRESS'>selected="selected"</#if>
                                value="IN_PROGRESS"> In progress
                        </option>
                        <option <#if task.status.toString()=='DONE'>selected="selected"</#if>
                                value="DONE"> Done
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;">Priority</td>
                <td style="text-align: center;">${task.priority}</td>
                <td style="text-align: center;">
                    <input id="priority" name="priority" value="${task.priority}" required>
                </td>
            </tr>
            </tbody>
        </table>
        <button type="submit">
            Apply changes
        </button>
    </form>
</body>
</html>