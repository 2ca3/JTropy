<%@ page contentType="text/html;charset=UTF-8" language="java" %><html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-script-type" content="text/javascript" />
    <meta http-equiv="content-style-type" content="text/css" />
    <link rel="stylesheet" href="css/jtropy.css" type="text/css">
    <title>
    JTropy
    </title>
  </head>
  <body>
    <div class="main">
      <table class="tbl">
        <tr>
          <td>
            <a href="/">
              <img alt="JTropy" src="images/logo.png" border="0" />
            </a>
          </td>
        </tr>
        <tr>
          <td class="btn">
            <a href="/e"><img alt="Create" src="images/create.png" border="0" /></a>
            <a href="/e<%if (request.getAttribute("key") != null) out.print("?key=" + request.getAttribute("key")); %>"><img alt="Edit" src="images/edit.png" border="0" /></a>
            <a href="/"><img alt="Random" src="images/random.png" border="0" /></a>
          </td>
        </tr>
        <tr>
          <td class="title">
            <%= request.getAttribute("title") %>
          </td>
        </tr>
        <tr>
          <td class="body">
            <%= request.getAttribute("body") %>
          </td>
        </tr>
      </table>
    </div>
  </body>
</html>
