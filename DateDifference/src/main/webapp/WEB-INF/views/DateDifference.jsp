<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<body>
<h2>Calculate number of days between dates</h2>
<form name="DateForm" action="Calculate" method="post">
<c:if test="${!empty InvalidDate}"><p><c:out value="${InvalidDate}"/></p></c:if>
<div class="date">
    <label for="date1">Date 1 (mm/dd/yyyy):</label>
	<input name="date1" id="date1" type="text" min="0" max="10" size="15" value="<c:out value="${date1}"/>">
</div>
<div class="date">
    <label for="date2">Date 2 (mm/dd/yyyy):</label>
	<input name="date2" id="date2" type="text" min="0" max="10" size="15" value="<c:out value="${date2}"/>">
</div>
<c:if test="${!empty totalDaysMessage}">
<p><c:out value="${totalDaysMessage}" /></p>
</c:if>
<input type="reset">
<input type="submit" value="Calculate">
</form>
</body>
</html>