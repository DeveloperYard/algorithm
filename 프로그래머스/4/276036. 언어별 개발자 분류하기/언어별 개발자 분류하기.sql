# 다중 with
WITH FrontEndSkills AS (
    select *
    from skillcodes
    where category = 'Front End'
), PythonSkills AS (
    select *
    from skillcodes
    where name = 'Python'
), CsharpSkills AS (
    select *
    from skillcodes
    where name = 'C#'
), DevelopersGrade AS (
    select case 
        when (d.skill_code & (select sum(code) from FrontEndSkills)) > 0
            and (d.skill_code & (select code from PythonSkills)) = (select code from PythonSkills) then 'A'
        when (d.skill_code & (select code from CsharpSkills)) = (select code from CsharpSkills) then 'B'
        when (d.skill_code & (select sum(code) from FrontEndSkills)) > 0 then 'C'
        else null
    end as GRADE, d.id, d.email
    from Developers d
)

select dg.GRADE,
        d.ID,
        d.EMAIL
from developers d
    left join DevelopersGrade dg on d.id = dg.id
where GRADE is not null
order by GRADE, ID;