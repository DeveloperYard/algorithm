select fi.ID, fn.FISH_NAME, LENGTH
from (
    select fish_type, max(length) as mxLen
    from fish_info
    group by fish_type
) as MxLen
    left join fish_info fi on MxLen.fish_type = fi.fish_type and MxLen.mxLen = fi.length
    left join fish_name_info fn on fi.fish_type = fn.fish_type
order by fi.id asc;
