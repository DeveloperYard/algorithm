-- 코드를 작성해주세요
select ed.ID
from (
    -- select 2nd generation, recursive?
    select ed2.ID, ed2.parent_id
    from ECOLI_DATA ed1 -- ed1 -> 1세대
        left join ECOLI_DATA ed2 on ed1.id = ed2.parent_id and ed1.parent_id is null
    where ed2.ID is not null
) as AA
left join ECOLI_DATA ed on ed.parent_id = AA.id
where ed.id is not null
order by ed.id asc;


