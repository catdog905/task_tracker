<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="ru">
<head>
    <title>Task information</title>
    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="card">
    <form method="get" action="/projects_list/${task.project.id}">
        <button type="submit"  class="btn btn-primary btn-lg">
            BACK
        </button>
    </form>
    <form method="post" action="/task_list/${task.id}/submit_data">
        <div class="card-header">
        <h1>Task: ${task.name} </h1>
            </div>
        <div class="card-body">
        <table><tbody><tr>
                <td>
        <table>
            <tbody>
            <tr>
                <td>
                    <b>Type new task name:</b>
                </td>
                <td>

                    <input id="name" name="name" value="${task.name}" required>
                </td>
            </tr>
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
                </td>
                <td>
            <table style="border-collapse: collapse; width: 100%;">
                <tbody>
                <tr>
                    <td >
                        <h4>Description:</h4>
                    </td>
                    <td>
                        <div class="wrapper">
                            <textarea cols="60" rows="5" id="description" name="description">${task.description}</textarea>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
                </td>
            </tr></tbody></table>
        <button type="submit" class="btn btn-primary btn-lg btn-block">
            Apply changes
        </button>
        </div>
    </form>
    </div>
</div>
</body>
</html>