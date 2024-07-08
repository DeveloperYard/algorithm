with FrontSkills AS (
    select name, category, code
    from skillcodes
    where category = 'Front End'
)

select ID, EMAIL, FIRST_NAME, LAST_NAME
from developers d
where exists (
    select 1
    from FrontSkills f
    where (d.skill_code & f.code = f.code)
)
order by 1 asc;