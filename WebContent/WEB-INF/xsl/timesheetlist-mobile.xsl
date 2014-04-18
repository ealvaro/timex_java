<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:text>&lt;timesheetlist&gt;</xsl:text>
		<xsl:for-each select="timesheets/timesheet">
			<xsl:text>&lt;timesheet&gt;</xsl:text>
			<xsl:text>&lt;timesheetid&gt;</xsl:text>
			<xsl:value-of select="timesheetid" />
			<xsl:text>&lt;/timesheetid&gt;</xsl:text>
			<xsl:text>&lt;periodEndingDate&gt;</xsl:text>
			<xsl:value-of select="periodEndingDate" />
			<xsl:text>&lt;/periodEndingDate&gt;</xsl:text>
			<xsl:text>&lt;statusCode&gt;</xsl:text>
			<xsl:value-of select="statusCode" />
			<xsl:text>&lt;/statusCode&gt;</xsl:text>
			<xsl:text>&lt;/timesheet&gt;</xsl:text>
		</xsl:for-each>
		<xsl:text>&lt;/timesheetlist&gt;</xsl:text>
	</xsl:template>
</xsl:stylesheet>