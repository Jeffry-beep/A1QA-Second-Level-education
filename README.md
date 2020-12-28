# A1QA-Second-Level-education
# Task 1
В файле config.properties укажите для переменных username, password свой логин и пароль.

# Task 8
В файле config.properties укажите для переменных testrail_username, testrail_password, testrail_user_id, testrail_test_id ваши значения.

# Task 9
В файле config.properties укажите для переменных username, user_email, user_password, user_token, user_id необходимые значения.

# Final Task

В файле config.properties укажите для переменных app_username, app_password пароль и логин от приложения. Для переменных db_username, db_password пароль и логин от приложения от базы данных. Для переменных testrail_username, testrail_password, testrail_user_id, testrail_test_id соответсвующие значения. Если вы хотите сменить url приложения, базы данных или testrail - они меняются в этом же файле.

В файле testng.xml вы можете указать следующие параметры:"variant_number" - номер варианта выполняемого задания, "project_name" - название проекта для 2-ого шага, "saving_project" - название сохраняемого проекта для 4-ого шага, "adding_test" - название добавляемого теста для 5-ого шага, "adding_test_method" - название тестового метода добавляемого в 5-ом шаге теста, "adding_test_env" - название хост машины на котором выполнялся добавляемый в 5-ом шаге тест, "adding_test_log" -  название лога  теста добавляемого в 5-ом шаге.

Для демонстрации работы приложения, в частности добавления скриншотов и логов, в репозитории уже есть скриншот и лог. Вы можете добавить свои логи или скриншоты. Пути по умолчанию для них указаны в файле config.properties (переменные adding_logs_path и screenshots_path). Также не забудьте указать в файле testng.xml название добавляемого лога (параметр adding_test_log).

