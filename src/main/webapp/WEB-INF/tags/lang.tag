<%@ tag language="java" pageEncoding="UTF-8"%>

<form id="languageForm">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${locale.language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${locale.language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>