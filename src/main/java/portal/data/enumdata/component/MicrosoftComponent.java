package portal.data.enumdata.component;

public enum MicrosoftComponent {

    VISIO_PROFESSIONAL("visio_professional"),
    SHAREPOINT_SERVER_STANDARD("sharepoint_server_standard"),
    SERVER_SUITE_STANDARD("core_server_suite_standard"),
    SQL_SERVER_STANDARD_CORE("sql_server_standard_2_core"),
    EXCHANGE_BASIC("exchange_basic_2"),
    EXCHANGE_ENTERPRISE_PLUS("exchange_enterprise_plus_2"),
    VISIO_STANDARD("visio_standard"),
    PROJECT_PROFESSIONAL("project_professional"),
    WINDOWS_SERVER_DATA_CENTER("windows_server_datacenter"),
    SQL_SERVER_ENTERPRISE("sql_server_enterprise_2_core"),
    OFFICE_STANDARD("office_standard_1_user"),
    EXCHANGE_ENTERPRISE("exchange_enterprise_2"),
    OFFICE_PROFESSIONAL_PLUS("office_professional_plus"),
    PROJECT_SERVER("project_server"),
    WINDOWS_SERVER_STANDARD("windows_server_standard"),
    REMOTE_DESKTOP_SERVICE("remote_desktop_service"),
    SQL_SERVER_STANDARD("sql_server_standard_1_user"),
    EXCHANGE_STANDARD_PLUS("exchange_standard_plus_2"),
    PROJECT("project"),
    SHAREPOINT_SERVER_PROFESSIONAL("sharepoint_server_professional"),
    SERVER_SUITE_DATA_CENTER("core_server_suite_datacenter"),
    SQL_SERVER_WEB("sql_server_web_2_core"),
    EXCHANGE_STANDARD("exchange_standard_2"),
    WINDOWS_SERVER_STANDARD_VM("windows_server_standard_vm"),
    RIGHTS_MANAGEMENT_SERVICES("rights_menegement_services_1_user"),
    OFFICE_MULTI_LANGUAGE("office_multi_language_pack_1_user"),
    VISUAL_STUDIO("visualstudio_1_user"),
    VISUAL_STUDIO_BASIC("visualstudio_basic_1_user"),
    VISUAL_STUDIO_PROFESSIONAL("visualstudio_professional_1_user"),
    VISUAL_STUDIO_TEST_PROFESSIONAL("visualstudio_test_professional_1_user"),
    VISUAL_STUDIO_ENTERPRISE("visualstudio_enterprise_1_user"),
    SHAREPOINT_SERVER("sharepoint_server_1_user"),
    SHAREPOINT_HOSTING("sharepoint_hosting_1_server");

    private String component;

    MicrosoftComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component;
    }
}
