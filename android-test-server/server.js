const express = require('express')
const cors = require('cors')
const { urlencoded } = require('express')

const app = express()

app.use(cors())
app.use(express.json())
app.use(urlencoded({ extended: "false"}))

app.post("/login", (req, res) => {
    const { username, password } = req.body
    console.log(username, password)

    if(username === "pepper" && password =="123123"){
        res.status(200).json({
            message: "로그인 성공"
        })
    }else {
        res.status(403).json({
            message: "로그인 실패"
        })
    }
})

app.listen(5000, () => {
    console.log('server started..')
})