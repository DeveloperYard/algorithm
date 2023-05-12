def sol(s):
    ans = ""

    s = list(s)

    alpArr = [0]*26

    for i in s:
        alpArr[ord(i)-97] += 1

    for i in range(26):
        if alpArr[i]%2==1:
            ans += chr(i + 97)

    return ans


t = int(input())

for i in range(1, t+1):
    s = input()
    answer = sol(s)
    res = "Good" if answer=="" else answer
    print("#"+str(i)+" "+res)