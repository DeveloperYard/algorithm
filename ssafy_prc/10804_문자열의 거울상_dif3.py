def sol(s):
    ans = ""
    length = len(s)//2
    s = list(s)
    for i in range(length):
        s[i], s[len(s)-i-1] = s[len(s)-i-1], s[i]

    for i in s:
        if i == 'p':
            ans += 'q'
        elif i == 'q':
            ans += 'p'
        elif i == 'd':
            ans += 'b'
        else:
            ans += 'd'

    return ans


t = int(input())

for i in range(1, t+1):
    s = input()
    res = sol(s)
    print("#"+str(i)+" "+res)