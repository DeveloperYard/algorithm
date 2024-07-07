-- 코드를 작성해주세요
with FrontEnd AS (
    select name, category, code
    from skillcodes
    where category = 'Front End'
), PythonSkills AS (
    select name, category, code
    from skillcodes
    where name = 'Python'
), CsharpSkills AS (
    select name, category, code
    from skillcodes
    where name = 'C#'
), DeveloperGrade AS (
    select d.id, d.email, d.first_name, d.last_name,
        CASE 
            WHEN (d.SKILL_CODE & (SELECT CODE FROM PythonSkills)) = (SELECT CODE FROM PythonSkills)
                 AND (d.SKILL_CODE & (SELECT SUM(CODE) FROM FrontEnd)) > 0 THEN 'A'
            WHEN (d.SKILL_CODE & (SELECT CODE FROM CsharpSkills)) = (SELECT CODE FROM CsharpSkills) THEN 'B'
            WHEN (d.SKILL_CODE & (SELECT SUM(CODE) FROM FrontEnd)) > 0 THEN 'C'
            ELSE NULL
        END AS GRADE
    from developers d
)


select GRADE, d.ID, d.EMAIL
from developers d
    left join DeveloperGrade dg on d.id = dg.id
where GRADE is not null
order by GRADE, ID asc;